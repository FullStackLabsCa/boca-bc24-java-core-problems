package trading.serviceLayer;
import trading.Model.TradingValues;
import trading.RepositoryLayer.TradingRep;
import trading.Utility.FileNotExists;
import trading.Utility.HitErrorsThresholdException;
import trading.Utility.InvalidThresholdValueException;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static trading.PresentationLayer.TradingRunner.dataSource;
import static trading.PresentationLayer.TradingRunner.thresholdValue;
import static trading.serviceLayer.DataCheck.*;
public class TradingService {

    public static void readTradingFileAndWriteToQueue(String filePath) throws HitErrorsThresholdException, IOException, FileNotExists, SQLException {
        List<TradingValues> validBatch = new ArrayList<>();
        int rows = 0;
        int errorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            int lineIndex = 1;

            while ((line = reader.readLine()) != null) {
                rows++;
                line = line.trim();

                // Split data
                String[] data = line.split(",");
                if (data.length < 6) {
                    logErrorReader("Insufficient data fields in line: " + line, lineIndex);
                    errorCount++;
                    lineIndex++;
                    continue;
                }
                List<String> errorMessages = new ArrayList<>();

                // Validate fields
                boolean isValid = checkForValidTradeIdentifier(data[1], lineIndex, errorMessages) &&
                       checkForValidQuantity(data[3], lineIndex, errorMessages) &&
                     checkForValidPrice(data[4], lineIndex, errorMessages) &&
                        checkForValidTradeDate(data[5], lineIndex, errorMessages);
                if (!isValid) {
                    logErrorsReader(errorMessages, lineIndex);
                    errorCount++;
                } else {
                    TradingValues tradingValues = new TradingValues(
                            data[0], data[1], data[2],
                            Integer.parseInt(data[3]),
                            Double.parseDouble(data[4]),
                            LocalDate.parse(data[5])
                    );
                    System.out.println(tradingValues);
                    validBatch.add(tradingValues);
                }
                lineIndex++;
            }
        }
        double percentage = ((double) errorCount / rows) * 100;

        System.out.println("Total rows: " + rows);
        System.out.println("Total errors: " + errorCount);
        System.out.println("Error percentage in file: " + percentage + "%");

        if (percentage > thresholdValue) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " errors out of " + rows + " rows.");
        }
        if (!validBatch.isEmpty()) {
            TradingRep.insertdata(dataSource, validBatch);
        } else {
            System.out.println("No valid rows to insert into the database.");
        }
    }


    private static void logErrorReader(String errorMessage, int lineIndex) {
        String logFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/Reader_Error.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            String formattedMessage = String.format("Line %d: %s", lineIndex, errorMessage);
            System.out.println("Logging error: " + formattedMessage); // Debug print
            writer.write(formattedMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while logging errors: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void logErrorsReader(List<String> errorMessages, int lineIndex) {
        for (String message : errorMessages) {
            logErrorReader(message, lineIndex);
        }
    }
    public static void fetchThresholdValue() throws InvalidThresholdValueException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/serviceLayer/application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }
            properties.load(input);
            thresholdValue = Double.parseDouble(properties.getProperty("error.threshold"));
            if (thresholdValue < 1 || thresholdValue > 100) {
                throw new InvalidThresholdValueException("Enter valid value between 1 and 100.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties file: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Invalid threshold value: " + e.getMessage());
        }
    }
}
