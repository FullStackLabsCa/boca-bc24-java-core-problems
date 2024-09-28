package problems.jdbc.trading.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.exception.InvalidThresholdValueException;
import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.repository.InsertTradeRepository;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TradeService implements ReadFile, Threshold, ReadAndWriteErrorLog, PrintSummary {
    HikariDataSource dataSource;

    @Override
    public void readFileAndInitializeDataSource(String path, double thresholdValue, HikariDataSource hikariDataSource) {
        dataSource = hikariDataSource;
        ErrorChecking.setThreshold(thresholdValue);
        if (ErrorChecking.getThreshold() == 0) ErrorChecking.setThreshold(fetchThresholdValue());
        try {
            Map<Integer, Trade> trades = readCSVFile(path);
            InsertTradeRepository insertTradeRepository = new InsertTradeRepository();
            if (!trades.isEmpty()) insertTradeRepository.insertTrade(trades, dataSource);
        } catch (HitErrorsThresholdException | IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        } finally {
            printSummary(ErrorChecking.getRecords(), ErrorChecking.getInsertions(), ErrorChecking.getErrorCount());
        }
    }

    @Override
    public double fetchThresholdValue() {
        Properties properties = new Properties();
        double localThreshold = 0;
        try (InputStream input = TradeService.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }
            properties.load(input);
            localThreshold = Double.parseDouble(properties.getProperty("error.threshold"));
            if (localThreshold < 1 || localThreshold > 100)
                throw new InvalidThresholdValueException("Enter valid value");
        } catch (IOException e) {
            System.out.println("File not found Exception.");
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Enter valid value");
        }
        return localThreshold;
    }

    @Override
    public Map<Integer, Trade> readCSVFile(String path) throws IOException,
            HitErrorsThresholdException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            Map<Integer, Trade> trades = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                ErrorChecking.incrementRecordCount();
                Trade trade = createTradeRecord(line);
                if (trade != null) {
                    trades.put(ErrorChecking.getRecords(), trade);
                } else {
                    ErrorChecking.incrementErrorCount();
                    readFromFileErrorLog("/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src/problems/jdbc" +
                                    "/trading/logs/readerErrorLog.txt",
                            new Date() + " - parsing failed at line number -> " + ErrorChecking.getErrorCount());
                }
            }
            if (isThresholdExceeded()) {
                throw new HitErrorsThresholdException("File parsing failed...");
            }
            return trades;
        }
    }

    public Trade createTradeRecord(String line) {
        try {
            String[] tradeArray = line.split(",");
            return new Trade(tradeArray[0], tradeArray[1], tradeArray[2], Integer.parseInt(tradeArray[3]), Double.parseDouble(tradeArray[4]), LocalDate.parse(tradeArray[5]));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void printSummary(int records, int insertions, int errors) {
        System.out.println("Summary:");
        System.out.println("Records processed: " + records);
        System.out.println("Successful inserts: " + insertions);
        System.out.println("Error count: " + errors);
    }

    @Override
    public void readFromFileErrorLog(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();  // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred while writing read file error log.");
        }
    }

    @Override
    public void writeErrorLog(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();  // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred while writing insertion error log.");
        }
    }

    @Override
    public boolean isThresholdExceeded() {
        double errorRate = ((double) ErrorChecking.getErrorCount() / ErrorChecking.getRecords()) * 100;
        return errorRate > ErrorChecking.getThreshold();
    }

    @Override
    public double validateThreshold(String thresholdString) throws InvalidThresholdValueException {
        double threshold = 0;
        try {
            threshold = Double.parseDouble(thresholdString);
            if (threshold < 1 || threshold > 100)
                throw new InvalidThresholdValueException("Enter a valid threshold value" +
                        ".");
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Enter a valid threshold value.");
        }
        return threshold;
    }
}
