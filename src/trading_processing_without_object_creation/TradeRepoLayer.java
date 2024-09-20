package trading_processing_without_object_creation;

import java.io.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TradeRepoLayer {


    public static void insertTrade(Connection connection, double userThreshold) {
        double entriesAddedToBatchCounter = 0, entriesAddedToLogFileCounter = 0, noOfBatches = 0;

        File file = new File("trade_data.csv");

        String insertQuery = "insert into Trades (trade_id,ticker_symbol,quantity,price,trade_date) values (?,?,?,?,?)";

        try (Connection con = connection;
             Scanner scanner = new Scanner(new FileReader(file));
             PreparedStatement insertStat = con.prepareStatement(insertQuery);) {
            //TO SKIP THE FIRST LINE
            scanner.nextLine();

            String currentLine = scanner.nextLine();

            double entries = TradeFileReader.countEntries();

            FIRST:while (!currentLine.isBlank()) {
                String currentTransaction = currentLine;
                String[] splitTransaction = currentTransaction.split(",|\n");
                    con.setAutoCommit(false);

                    try {
                        if (splitTransaction[0].isEmpty() || splitTransaction[1].isEmpty()) {
                            throw new IllegalArgumentException("Missing Fields");
                        }
                        insertStat.setString(1, splitTransaction[0]);
                        insertStat.setString(2, splitTransaction[1]);
                        insertStat.setInt(3, Integer.parseInt(splitTransaction[2]));
                        insertStat.setDouble(4, Double.parseDouble(splitTransaction[3]));
                        insertStat.setDate(5, Date.valueOf(splitTransaction[4]));
                        insertStat.addBatch();
                        entriesAddedToBatchCounter++;
                    } catch (InputMismatchException | IllegalArgumentException | ArrayIndexOutOfBoundsException |
                             NullPointerException e) {
                        System.out.println("Error while Parsing data");
                        LogFileWriter.writeLog(currentTransaction);
                        entriesAddedToLogFileCounter++;
                        double threshold = (entriesAddedToLogFileCounter / entries)*100;
                        if (threshold >= userThreshold) {
                            connection.rollback();
                            throw new HitErrorsThresholdException("Threshold Reached....");
                        }
                    }
                    if (entriesAddedToBatchCounter == 50) {
                        insertStat.executeBatch();
                        entriesAddedToBatchCounter = 0;
                    }
                    if (!scanner.hasNextLine())break FIRST;else currentLine=scanner.nextLine();
                }
            if (entriesAddedToBatchCounter > 0) {
                insertStat.executeBatch();
            }
                connection.commit();
                connection.setAutoCommit(true);

        } catch (FileNotFoundException |
                 SQLException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}



