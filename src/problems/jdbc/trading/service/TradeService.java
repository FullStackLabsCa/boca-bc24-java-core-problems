package problems.jdbc.trading.service;

import java.io.*;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.database.DatabaseConnection;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.exception.InvalidThresholdValueException;
import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.repository.TradeRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class TradeService implements TradeServiceInterface {
    static HikariDataSource dataSource;

    @Override
    public void readFileAndInitializeDataSource(String path, double thresholdValue) {
        ErrorChecking errorChecking = new ErrorChecking();
        errorChecking.setThreshold(thresholdValue);
        dataSource = DatabaseConnection.configureHikariCP("3306", "transactions");
        if (errorChecking.getThreshold() == 0) errorChecking.setThreshold(fetchThresholdValue());
        try {
            Map<Integer, Trade> trades = readCSVFile(path, errorChecking);
            TradeRepository tradeRepository = new TradeRepository();
            if (!trades.isEmpty()) tradeRepository.insertTrade(trades, dataSource, errorChecking);
        } catch (HitErrorsThresholdException | IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            printSummary(errorChecking.getRecords(), errorChecking.getInsertions(), errorChecking.getErrorCount());
        }
    }

    @Override
    public double fetchThresholdValue() {
        Properties properties = new Properties();
        double localThreshold;
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
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Enter valid value");
        }
        return localThreshold;
    }

    @Override
    public Map<Integer, Trade> readCSVFile(String path, ErrorChecking errorChecking) throws IOException,
            HitErrorsThresholdException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        reader.readLine();
        Map<Integer, Trade> trades = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            errorChecking.incrementRecordCount();
            Trade trade = createTradeRecord(line);
            if (trade != null) {
                trades.put(errorChecking.getRecords(), trade);
            } else {
                errorChecking.incrementErrorCount();
                readFromFileErrorLog("/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src/problems/jdbc" +
                                "/trading/logs/readerErrorLog.txt",
                        new Date() + " - parsing failed at line number -> " + errorChecking.getErrorCount());
            }
        }
        if (isThresholdExceeded(errorChecking)) {
            throw new HitErrorsThresholdException("File parsing failed...");
        }
        return trades;
    }

    @Override
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
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    @Override
    public void writeErrorLog(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();  // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    @Override
    public boolean isThresholdExceeded(ErrorChecking errorChecking) {
        double errorRate = ((double) errorChecking.getErrorCount() / errorChecking.getRecords()) * 100;
        return errorRate > errorChecking.getThreshold();
    }
}
