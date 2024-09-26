package trading.service;
import trading.Model.TradingValues;
import trading.repository.TradingRep;
import trading.utility.FileNotExists;
import trading.utility.HitErrorsThresholdException;
import trading.utility.InvalidThresholdValueException;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static trading.presentation.TradingRunner.DATA_SOURCE;
import static trading.presentation.TradingRunner.THRESHOLD_VALUE;
import static trading.service.DataCheck.*;
public class TradingService {
    private TradingService(){
    }

    public static void readTradingFileAndWriteToQueue(File filePath) throws HitErrorsThresholdException, IOException, FileNotExists, SQLException  {
        List<TradingValues> validBatch = new ArrayList<>();
        int rows = 0;
        int errorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line=reader.readLine();
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
        double percentage=0;
        if(rows>0){
            percentage = ((double) errorCount / rows) * 100;
        }

        System.out.println("Total rows: " + rows);
        System.out.println("Total errors: " + errorCount);
        System.out.println("Error percentage in file: " + percentage + "%");

        if (percentage < THRESHOLD_VALUE) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " errors out of " + rows + " rows.");
        }
        if (!validBatch.isEmpty()) {
            TradingRep.insertdata(DATA_SOURCE, validBatch);
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

    public static void fetchThresholdValue() throws IOException, FileNotExists {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/serviceLayer/application.properties")) {
            // No need to check for null; handle exceptions accordingly
            properties.load(input);
            THRESHOLD_VALUE = Double.parseDouble(properties.getProperty("error.threshold"));

            // Validate the threshold value
            if (THRESHOLD_VALUE < 1 || THRESHOLD_VALUE > 100) {
                throw new InvalidThresholdValueException("Enter a valid value between 1 and 100.");
            }
        } catch (FileNotFoundException | InvalidThresholdValueException e) {
            throw new FileNotExists("Unable to find application.properties: " + e.getMessage());
        }
    }





}
