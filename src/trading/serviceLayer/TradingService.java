//package trading.serviceLayer;
//import trading.Model.TradingValues;
//import trading.RepositoryLayer.TradingRep;
//import trading.Utility.FileNotExists;
//import trading.Utility.HitErrorsThresholdException;
//import trading.Utility.InvalidThresholdValueException;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import static trading.PresentationLayer.TradingRunner.dataSource;
//import static trading.PresentationLayer.TradingRunner.thresholdValue;
//
//public class TradingService {
//    public static void readTradingFileAndWriteToQueue(String filePath) throws HitErrorsThresholdException, IOException, FileNotExists, SQLException {
//        List<TradingValues> validBatch = new ArrayList<>();
//        int rows = 0;
//        int error = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            reader.readLine();
//            while ((line = reader.readLine()) != null) {
//                rows++;
//                line = line.trim();
//                if(DataValidation.checkForValidTradeDate(line)){
//                    TradingRep.errorLog(line);
//                    error++;
//                    continue;
//
//                }
//                try {
//                    String[] data = line.split(",");
//                    if (data.length < 6) {
//                        System.out.println("Insufficient data fields.");
//                        error++;
//                        continue;
//                    }
//                    TradingValues tradingValues = new TradingValues(data[0], data[1],data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), LocalDate.parse(data[5]));
//                    System.out.println(tradingValues);
//                    validBatch.add(tradingValues);
//                }
//                catch (NumberFormatException e) {
//                    error++;
//                } catch (IllegalArgumentException e) {
//                    error++;
//                } catch (Exception e) {
//                    error++;
//                }
//            }
//        }
//        double percentage = ((double) error / (rows)) * 100;
//    //    System.out.println("Total rows: " + rows + ",  Invalid rows: " + error +"  Valid rows :  "+ (rows- error));
//        if (percentage > thresholdValue) {
//            throw new HitErrorsThresholdException("Error threshold exceeded: " + error + " errors out of " + rows + " rows.");
//        }
//            TradingRep.insertdata(dataSource, validBatch);
//            System.out.println(validBatch.size() + " rows added in database");
//        }
////        else {
////            try {
//////                TradingRep.insertdata(dataSource, validBatch);
//////                System.out.println(validBatch.size() + " rows added in database");// correct
////            } catch (SQLException e) {
////                error++;
////                throw new RuntimeException(e.getMessage());
////            }
////        }
//   //     TradingRep.insertdata(dataSource,validBatch);
//    //    System.out.println();
//
//
//
//        public static void fetchThresholdValue() {
//            Properties properties = new Properties();
//            try (InputStream input = TradingRep.class.getClassLoader().getResourceAsStream("application.properties")) {
//                if (input == null) {
//                    System.out.println("Sorry, unable to find application.properties");
//                    System.exit(1);
//                }
//                properties.load(input);
//                thresholdValue = Double.parseDouble(properties.getProperty("error.threshold"));
//                if (thresholdValue < 1 || thresholdValue > 100) throw new InvalidThresholdValueException("Enter valid value");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (NumberFormatException e) {
//                throw new InvalidThresholdValueException("Enter valid value");
//            }
//        }
//    }
package trading.serviceLayer;

import trading.Model.TradingValues;
import trading.RepositoryLayer.TradingRep;
import trading.Utility.FileNotExists;
import trading.Utility.HitErrorsThresholdException;
import trading.Utility.InvalidThresholdValueException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static trading.PresentationLayer.TradingRunner.dataSource;
import static trading.PresentationLayer.TradingRunner.thresholdValue;

public class TradingService {
    public static void readTradingFileAndWriteToQueue(String filePath) throws HitErrorsThresholdException, IOException, FileNotExists, SQLException {
        List<TradingValues> validBatch = new ArrayList<>();
        int rows = 0;
        int errorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                rows++;
                line = line.trim();

                // Validate trade date
                if (DataValidation.checkForValidTradeDate(line)) {
                    TradingRep.errorLog("Invalid trade date in line: " + line);
                    errorCount++;
                    continue;
                }
                try {
                    String[] data = line.split(",");
                    if (data.length < 6) {
                        TradingRep.errorLog("Insufficient data fields in line: " + line);
                        errorCount++;
                        continue;
                    }

                    TradingValues tradingValues = new TradingValues(
                            data[0], data[1], data[2],
                            Integer.parseInt(data[3]),
                            Double.parseDouble(data[4]),
                            LocalDate.parse(data[5])
                    );
                    System.out.println(tradingValues);
                    validBatch.add(tradingValues);
                } catch (NumberFormatException e) {
                    TradingRep.errorLog("Number format error in line: " + line + " - " + e.getMessage());
                    errorCount++;
                } catch (IllegalArgumentException e) {
                    TradingRep.errorLog("Illegal argument in line: " + line + " - " + e.getMessage());
                    errorCount++;
                } catch (Exception e) {
                    TradingRep.errorLog("Unexpected error in line: " + line + " - " + e.getMessage());
                    errorCount++;
                }
            }
        }

        // Calculate the error percentage
        double percentage = ((double) errorCount / rows) * 100;

        // Print summary of errors
        System.out.println("Total rows : " + rows);
        System.out.println("Total errors: " + errorCount);
        System.out.println("Error percentage in file : " + percentage + "%");

        // Check if error percentage exceeds threshold
        if (percentage > thresholdValue) {
           throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " errors out of " + rows + " rows.");
        }

        // Insert valid data into the database if there are valid rows
        if (!validBatch.isEmpty()) {
            TradingRep.insertdata(dataSource, validBatch);
        } else {
            System.out.println("No valid rows to insert into the database.");
        }
        System.out.println((errorCount + rows- validBatch.size())+ "rows are having invalid data.");

    }

    public static void fetchThresholdValue() {
        Properties properties = new Properties();
        try (InputStream input = TradingRep.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }
            properties.load(input);
            thresholdValue = Double.parseDouble(properties.getProperty("error.threshold"));
            if (thresholdValue < 1 || thresholdValue > 100) {
                throw new InvalidThresholdValueException("Enter valid value");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Enter valid value");
        }
    }
}
