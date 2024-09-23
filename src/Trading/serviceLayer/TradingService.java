package Trading.serviceLayer;

import Trading.model.TradingValues;
import Trading.repoLayer.TradingRep;
import Trading.utility.FileNotExists;
import Trading.utility.HitErrorsThresholdException;
import Trading.utility.InvalidThresholdValueException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static Trading.presentationLayer.TradingRunner.dataSource;
import static Trading.presentationLayer.TradingRunner.thresholdValue;

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
        double percentage = ((double) errorCount / rows) * 100;

        // Print summary of errors
        System.out.println("Total rows : " + rows);
        System.out.println("Total errors: " + errorCount);
        System.out.println("Error percentage in file : " + percentage + "%");

        if (percentage > thresholdValue) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " errors out of " + rows + " rows.");
        }

        if (!validBatch.isEmpty()) {
            TradingRep.insertdata(dataSource, validBatch);
        } else {
            System.out.println("No valid rows to insert into the database.");
        }
        System.out.println((errorCount + rows- validBatch.size())+ "rows are having invalid data.");

    }
    public static void fetchThresholdValue() throws InvalidThresholdValueException {
        Properties properties = new Properties();
        try (InputStream input = TradingService.class.getClassLoader().getResourceAsStream("/Users/Gagandeep.Kaur/source/students/tradeproject/src/Trading/utility/application.properties")) {
            if (input == null) {
                System.out.println("Unable to find application.properties");
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
            throw new InvalidThresholdValueException("Enter valid value"+e.getMessage());
        }
    }
}