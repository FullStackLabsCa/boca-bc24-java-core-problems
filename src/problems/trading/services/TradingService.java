package problems.trading.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.DataValidation;
import problems.trading.customexceptions.HitErrorsThresholdException;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.repository.TradingRepository;
import problems.trading.tradingmodel.TradingValues;
import static problems.trading.TradingProcessor.filePath;
import static problems.trading.TradingProcessor.dataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class TradingService {

    public static int errorThreshold = 25;

    public static void setupDBConnectionAndRunFileReading(Connection connection, String filePath, double errorThreshold) {
        readTradingFileAndWriteToFile(filePath, errorThreshold);
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
    public static void readTradingFileAndWriteToFile(String filePath, double errorThreshold) {

        List<TradingValues> batch = new ArrayList<>();
        //List<String> errorLogForReading = new ArrayList<>();
        double rowCounter = 0;
        double errorCounter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                rowCounter++;
                double lineNumber = rowCounter;


                String[] data = line.split(",");

                try {
                    if (!DataValidation.checkForAllValidations(line, connectToDatabase(), lineNumber)) { //check
                        errorCounter++;
                        // errorLogForReading.add("Error in row: " + rowCounter);
                       continue;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    errorCounter++;
                    //  errorLogForReading.add("Error in row: " + rowCounter + e.getMessage());
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
               // break;

            }
            TradingRepository.prepareStatements(dataSource, batch);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //  double percentErrorInReadingFile = 0;
        double maxErrorPercentageAcceptableForReading = Math.ceil(rowCounter * (errorThreshold / 100.00));
        System.out.println("ERROR_THRESHOLD = " + errorThreshold);
        System.out.println("Total number of rows in file: " + rowCounter + ", number of successfully added rows: " + batch.size() + ", number of rows that are failed: " + errorCounter + " , maximum  allowed percent error in reading file: " + maxErrorPercentageAcceptableForReading);

        if (maxErrorPercentageAcceptableForReading < errorCounter) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCounter + " out of " + rowCounter);
        }

        //  logReaderErrors(errors);
    }


    public static void logReaderErrors(String errorsInReading) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/trading/tradesutility/Trade_reading_error_log", true))) {
            writer.write(errorsInReading);
            writer.newLine();
            // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void logWritingErrors(String errorsInWriting) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/trading/tradesutility/Trade_writing_error_log", true))) {
            writer.write(errorsInWriting);
            writer.newLine();

            // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred while loading errors in Writing log.");
            e.printStackTrace();
        }
    }
}


