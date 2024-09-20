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
        int error = 0;
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                rows++;
                line = line.trim();
                counter++;
                try {
                    String[] data = line.split(",");
                    if (data.length < 6) {
                        throw new IllegalArgumentException("Insufficient data fields.");
                    }
                    TradingValues tradingValues = new TradingValues(data[0], data[1],data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), LocalDate.parse(data[5]));
                    System.out.println(tradingValues);
                    validBatch.add(tradingValues);
                } catch (NumberFormatException e) {
                    error++;
                } catch (IllegalArgumentException e) {
                    error++;
                } catch (Exception e) {
                    error++;
                }
            }
        }
        double percentage = ((double) error / rows) * 100;
   //     System.out.println("Total rows: " + rows + ",  Invalid rows: " + error +"  Valid rows :  "+ (rows- error));
//        if (percentage < thresholdValue) {
//            throw new HitErrorsThresholdException("Error threshold exceeded: " + error + " errors out of " + rows + " rows.");
//        } else {
//            try {
//                TradingRep.insertdata(dataSource, validBatch);
//                System.out.println(validBatch.size() + " rows added in database");
//            } catch (SQLException e) {
//                throw new RuntimeException(e.getMessage());
//            }
//        }
        TradingRep.insertdata(dataSource,validBatch);
    //    System.out.println();

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
                if (thresholdValue < 1 || thresholdValue > 100) throw new InvalidThresholdValueException("Enter valid value");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                throw new InvalidThresholdValueException("Enter valid value");
            }
        }
    }
