package problems.tradingplatform.services;

import problems.tradingplatform.maininterface.TradeInterface;
import problems.tradingplatform.customexceptions.HitErrorsThresholdException;
import problems.tradingplatform.databasehelpers.DatabaseHelper;

import static problems.tradingplatform.MainRunner.threshold;
import static problems.tradingplatform.services.ReadingWritingValidation.isValidRow;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TradingOperation implements Serializable , TradeInterface {
    static int totalReadingRows = 0;
    static int totalWritingRows = 0;
    public static int errorCount = 0;
    public static int validRowCount = 0;
    static int currentRowNumber = 0;

    public static List<String[]> errorRows = new ArrayList<>();
    private static final String ERROR_LOG_FILE = "error_log.txt";

    @Override
    public void tradeReadingOperation(String filePath, double threshold) throws HitErrorsThresholdException {
        List<String[]> validRows = new ArrayList<>();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                totalReadingRows++;
                currentRowNumber++;
                String[] row = line.split(",");

                if (isValidRow(row, currentRowNumber)) {
                    validRows.add(row);
                    validRowCount++;
                } else {
                    errorRows.add(row);
                    logError("Read_Err | ",Arrays.toString(row), "Invalid row format or data type.", currentRowNumber);
                }
            }
            System.out.println("--------READING PART-------");
//            "Total error count in code: " +
            System.out.println( errorCount);

            if (errorCount > totalReadingRows *  threshold) {
                throw new HitErrorsThresholdException("Error count exceeded 25% of the total rows.");

            } else {
//                "Total Valid rows: " +
                System.out.println( validRowCount);
                TradeWritingDAO(validRows, threshold);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(validRowCount);
        System.out.println(errorCount);
    }

    @Override
    public void TradeWritingDAO(List<String[]> validRows, double thresold) throws SQLException {
        String selectSymbolQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";

        String insertTradeQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";

        try(Connection connection = DatabaseHelper.getConnection()){
            try (PreparedStatement selectSymbolStmt = connection.prepareStatement(selectSymbolQuery);
                 PreparedStatement insertTradeStmt = connection.prepareStatement(insertTradeQuery)) {

                connection.setAutoCommit(false);

                int totalInsertedRows = 0;
//                System.out.println("Initial error count = "+errorCount);
                for (String[] row : validRows) {
                    totalWritingRows++;

                    String trade_id = row[0];
                    String trade_identifier = row[1];
                    String ticker_symbol = row[2];
                    int quantity = Integer.parseInt(row[3]);
                    double price = Double.parseDouble(row[4]);
                    java.sql.Date trade_date = Date.valueOf(row[5]);

                    try{
                        selectSymbolStmt.setString(1, ticker_symbol);
                        ResultSet resultSet = selectSymbolStmt.executeQuery();

                        if (resultSet.next()) {
                            insertTradeStmt.setString(1, trade_id);
                            insertTradeStmt.setString(2, trade_identifier);
                            insertTradeStmt.setString(3, ticker_symbol);
                            insertTradeStmt.setInt(4, quantity);
                            insertTradeStmt.setDouble(5, price);
                            insertTradeStmt.setDate(6, trade_date);

                            insertTradeStmt.addBatch();
                            totalInsertedRows++;
                        }
                        else if(!resultSet.next()) {
                            errorCount++;
                            logError("Insert_Err | ",Arrays.toString(row), "Invalid security symbol: "+ticker_symbol, Integer.valueOf(trade_id));
                        }
                    }catch (SQLException e){
                        if (e.getSQLState().startsWith("23")) { // SQLState 23XXX: Constraint violation (e.g., duplicates)
                            String errorMessage = e.getMessage();
                            logError("Insert_Err | ",Arrays.toString(row), "Duplicate entry or constraint violation.", null);
                        } else {
                            logError("Insert_Err | ",Arrays.toString(row), "SQL Error: " + e.getMessage(),null );
                        }
                    }
                }
                System.out.println("--------Writing PART-------");
                System.out.println("Error count = " + errorCount);
                System.out.println("Total rows from Writer class - "+totalWritingRows);
                System.out.println("Threshold value is - " + thresold);

                if(errorCount > totalWritingRows * thresold ) {
                    throw new HitErrorsThresholdException("Error count exceeded "+(threshold*100)+"% of the total rows.");
                }else{
                    insertTradeStmt.executeBatch();
                    connection.commit();
                }
                System.out.println(totalInsertedRows + " trades inserted.");
                System.out.println(totalWritingRows - totalInsertedRows + " having errors and they are stored in error_log file..");
            } catch (SQLException e) {
                System.out.println("\n Duplicate entry for trade_id therefore transaction is rollback. Please check the file and run again. ");
                throw new RuntimeException();
            } catch (HitErrorsThresholdException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void logError(String errorfrom, String row, String errorMessage, Integer rowNumber) {
        try (FileWriter fileWriter = new FileWriter(ERROR_LOG_FILE, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            printWriter.println(errorfrom + "Row " + rowNumber + ": " + " | Error: " + errorMessage +" | "+ row) ;
        } catch (IOException e) {
            System.err.println("Failed to write to error log file: " + e.getMessage());
        }
    }
}
