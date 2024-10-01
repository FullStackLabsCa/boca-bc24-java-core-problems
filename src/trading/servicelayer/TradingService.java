package trading.servicelayer;

import trading.model.TradingValues;
import trading.repolayer.TradingRep;
import trading.utility.FileNotExists;
import trading.utility.HitErrorsThresholdException;
import trading.utility.InvalidThresholdValueException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import static trading.presentationLayer.TradingRunner.dataSource;
import static trading.presentationLayer.TradingRunner.thresholdValue;

@SuppressWarnings("squid:S106")
public class TradingService {
    private TradingService() {
    }

    public static void readTradingFileAndWriteToQueue(String filePath) throws HitErrorsThresholdException, IOException, FileNotExists, SQLException {
        List<TradingValues> validBatch = new ArrayList<>();
        int tryCount = 0;
        int errorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String string = reader.readLine();// Skip header line
            while ((line = string) != null) {
                line = line.trim();
                // Validate trade date
                try {
                    tryCount++;

                    if (DataValidation.checkForValidTradeDate(line)) {
                        TradingRep.errorLog("Invalid trade date in line: " + line);
                        errorCount++;
                        continue;
                    }
                    if ((double) errorCount / tryCount > 0.25) {
                      throw new HitErrorsThresholdException("More than 25% of the rows have errors. Total Errors: " + errorCount);
                    }
                } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
                    try {
                        String[] fields = line.split(",");
                        if (fields.length < 6) {
                            TradingRep.errorLog("Insufficient fields fields in line: " + line);
                            errorCount++;
                            continue;
                        }

                        TradingValues tradingValues = new TradingValues(
                                fields[0], fields[1], fields[2],
                                Integer.parseInt(fields[3]),
                                Double.parseDouble(fields[4]),
                                LocalDate.parse(fields[5])
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
        double percentage = ((double) errorCount / tryCount) * 100;

        // Print summary of errors
        System.out.println("Total tryCount : " + tryCount);
        System.out.println("Total errors: " + errorCount);
        System.out.println("Error percentage in file : " + percentage + "%");

        if (percentage > thresholdValue) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " errors out of " + tryCount + " tryCount.");
        }

        if (!validBatch.isEmpty()) {
            TradingRep.insertData(dataSource, validBatch);
        } else {
            System.out.println("No valid tryCount to insert into the database.");
        }
        System.out.println((errorCount + tryCount- validBatch.size())+ " tryCount are having invalid data.");

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