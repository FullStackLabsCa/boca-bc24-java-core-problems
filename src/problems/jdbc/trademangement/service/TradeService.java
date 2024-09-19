package jdbc.trademangement.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.trademangement.databaseconnection.DatabaseConnection;
import jdbc.trademangement.exceptions.HitErrorsThresholdException;
import jdbc.trademangement.model.TradeData;
import jdbc.trademangement.repository.TradeRepository;
import jdbc.trademangement.utils.Utils;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TradeService {

    private static final List<TradeData> tradeDataList = new ArrayList<>(1000);;
    private static HikariDataSource dataSource;
    private static int errorCount = 0, maxExpectedErrors = 0, successCount = 0;

    public static void setupDbConnectionAndReadFile(String filePath) {
        // Step 1: Configure HikariCP connection pool
        dataSource = DatabaseConnection.configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTradeFileAndWriteToQueue(filePath);
    }

    public static int calculateFileLines(String filePath)  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readTradeFileAndWriteToQueue(String filePath) {
        int counter = 0;
        int lineCount = calculateFileLines(filePath);
        maxExpectedErrors = (int) Math.ceil(lineCount * 0.25);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split(",");

                boolean isValidString = Utils.validateInput(line);
                if (!isValidString) {
                    errorCount++;
                    if (maxExpectedErrors <= errorCount) {
                        throw new HitErrorsThresholdException("Hits maximum error threshold..." + errorCount);
                    }
                    continue;
                }
                TradeData tradeData = new TradeData(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4]);
                System.out.println("adding trade #" + counter + " in the queue >> " + tradeData);
                tradeDataList.add(tradeData);
            }

            processTradeData();

            System.out.println("=================Reports of the trade system==================");
            System.out.println("Total records processed = " + counter + ", Number of errors = " +  errorCount + ", Number of successful insert = " + successCount);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HitErrorsThresholdException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void processTradeData() {
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            int[] results = null;
            try {
                String insertQuery = "INSERT INTO Trades(trade_id, ticker_symbol, quantity, price, trade_date) VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                for (TradeData tradeData: tradeDataList) {
                    String tradeId = TradeRepository.getTradeId(connection, tradeData.getTrade_id());
                    if (tradeId != null) {
                        continue;
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate =  format.parse(tradeData.getTrade_date());
                    Date date = new Date(utilDate.getTime());

                    preparedStatement.setString(1, tradeData.getTrade_id());
                    preparedStatement.setString(2, tradeData.getTicker_symbol());
                    preparedStatement.setInt(3, tradeData.getQuantity());
                    preparedStatement.setDouble(4, tradeData.getPrice());
                    preparedStatement.setDate(5, date);
                    preparedStatement.addBatch();
                }
                results = preparedStatement.executeBatch();
                System.out.println("Count======" + results);

                if (maxExpectedErrors <= errorCount) {
                    connection.rollback();
                    throw new HitErrorsThresholdException("Hits maximum error threshold..." + errorCount);
                }
                connection.commit();
            } catch (BatchUpdateException e) {
                System.out.println(e.getMessage());

                // Get the update counts
                int[] updateCounts = e.getUpdateCounts();

                for (int count : updateCounts) {
                    if (count >= 0) {
                        successCount++;
                    } else if (count == Statement.EXECUTE_FAILED) {
                        errorCount++;
                    }
                }
                System.out.println("Successful records before failure: " + successCount);
                System.out.println("Failed records in batch: " + errorCount);
                if (maxExpectedErrors <= errorCount) {
                    connection.rollback();
                    throw new HitErrorsThresholdException("Hits maximum error threshold..." + errorCount);
                }
                connection.commit();
            }
            catch (SQLException e) {
                errorCount++;
                System.out.println("ERROR>>>" + e.getMessage());
                connection.rollback();
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("ERROR>>>" + e.getMessage());
            e.printStackTrace();
        }
    }
}
