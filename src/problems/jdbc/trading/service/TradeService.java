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

public class TradeService {
    static HikariDataSource dataSource;

    public static void readFileAndInitializeDataSource(String path, double thresholdValue) {
        ErrorChecking.setThreshold(thresholdValue);
        dataSource = DatabaseConnection.configureHikariCP("3306", "transactions");
        if (ErrorChecking.getThreshold() == 0) ErrorChecking.setThreshold(fetchThresholdValue());
        try {
            Map<Integer, Trade> trades = readCSVFile(path);
            if (!trades.isEmpty()) TradeRepository.insertTrade(trades, dataSource);
        } catch (HitErrorsThresholdException | IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            printSummary(ErrorChecking.getRecords(), ErrorChecking.getInsertions(), ErrorChecking.getErrorCount());
        }
    }

    public static double fetchThresholdValue() {
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
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Enter valid value");
        }
        return localThreshold;
    }

    public static Map<Integer, Trade> readCSVFile(String path) throws IOException, HitErrorsThresholdException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        reader.readLine();
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

    public static Trade createTradeRecord(String line) {
        try {
            String[] tradeArray = line.split(",");
            return new Trade(tradeArray[0], tradeArray[1], tradeArray[2], Integer.parseInt(tradeArray[3]), Double.parseDouble(tradeArray[4]), LocalDate.parse(tradeArray[5]));
        } catch (Exception e) {
            return null;
        }
    }

    public static void printSummary(int records, int insertions, int errors) {
        System.out.println("Summary:");
        System.out.println("Records processed: " + records);
        System.out.println("Successful inserts: " + insertions);
        System.out.println("Error count: " + errors);
    }

    public static void readFromFileErrorLog(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();  // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    public static void writeErrorLog(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Appending this line to the file.");
            writer.newLine();  // Add a new line
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    public static boolean isThresholdExceeded() {
        double errorRate = ((double) ErrorChecking.getErrorCount() / ErrorChecking.getRecords()) * 100;
        return errorRate > ErrorChecking.getThreshold();
    }
}
