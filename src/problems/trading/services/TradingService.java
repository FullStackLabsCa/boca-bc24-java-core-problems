package problems.trading.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.DataValidation;
import problems.trading.customexceptions.HitErrorsThresholdException;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.repository.TradingRepository;
import problems.trading.tradingmodel.TradingValues;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static problems.trading.DataValidation.checkForValidNumberOfColumns;
import static problems.trading.DataValidation.checkForValidQuantity;
import static problems.trading.TradingProcessor.dataSource;

public class TradingService {

    public static final int ERROR_THRESHOLD = 25;

    public static void setupDBConnectionAndRunFileReading(Connection connection, String filePath) {
        readTradingFileAndWriteToFile(filePath);
    }

    //connecting to database
    public static Connection connectToDatabase() {
        HikariDataSource dataSource = TradingDatabaseConnection.configureHikariCP();
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Step 1: Reading trading from a delimited file
    public static void readTradingFileAndWriteToFile(String filePath) {

        List<TradingValues> batch = new ArrayList<>();
        List<String> errorLogForReading = new ArrayList<>();
        double rowCounter = 0;
        double errorCounter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                rowCounter++;


                String[] data = line.split(",");

                try {
                    if (!DataValidation.checkForAllValidations(line)){
                        errorCounter++;
                        errorLogForReading.add("Error in row: " + rowCounter);
                       continue;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    errorCounter++;
                    errorLogForReading.add("Error in row: " + rowCounter + e.getMessage());
                    continue;
                }

                //trimming to remove any white spaces
                TradingValues tradingValues = new TradingValues(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        Integer.parseInt(data[3].trim()),
                        Double.parseDouble(data[4].trim()),
                        LocalDate.parse(data[5])
                );
                //System.out.println(tradingValues);
                batch.add(tradingValues);

            }
            TradingRepository.prepareStatements(dataSource, batch);

        } catch (FileNotFoundException f) {
            System.out.println("Wrong file path provided. Please provide the correct file path.");

        } catch (IOException e) {
            e.printStackTrace();
        }


        double percentErrorInReadingFile = ( errorCounter/rowCounter) * 100;
        System.out.println("Total number of rows in file: " + rowCounter + ", number of successfully added rows: " + batch.size() + ", number of rows that are failed: " + errorCounter + " , percent error in reading file: " + percentErrorInReadingFile);

        if (percentErrorInReadingFile > ERROR_THRESHOLD) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCounter + " out of " + rowCounter);
        }

        logReaderErrors(errorLogForReading);
    }

    public static void logReaderErrors(List<String> errorsInReading) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Trade_reading_error_log", true))) {
           for(String errorsWhileReading : errorsInReading) {
               writer.write(errorsWhileReading);
               writer.newLine();
           }// Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}




//        try (BufferedWriter writer = new BufferedWriter("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/trading/Trade_reading_error_log", true)) {
//            for (String error : errorsInReading) {
//                writer.write(error + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}



