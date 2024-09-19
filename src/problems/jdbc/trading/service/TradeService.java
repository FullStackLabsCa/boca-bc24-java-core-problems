package problems.jdbc.trading.service;

import java.io.*;
import java.sql.Connection;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.database.DatabaseConnection;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.exception.InvalidThresholdValueException;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.repository.TradeRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TradeService {
    static HikariDataSource dataSource;
    static double threshold = 0;
    static int records = 0;
    static int errorCount = 0;

    public static void readFileAndInitializeDataSource(String path, double thresholdValue) {
        threshold = thresholdValue;
        dataSource = DatabaseConnection.configureHikariCP("3306", "transactions");
        if (threshold == 0) fetchThresholdValue();
        try {
            List<Trade> trades = readCSVFile(path, errorCount, records, threshold);
            TradeRepository.insertTrade(trades, dataSource, errorCount, threshold, records);
        } catch (HitErrorsThresholdException | IOException e) {
            System.out.println(e.getMessage());
            printSummary(records, 0, errorCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fetchThresholdValue() {
        Properties properties = new Properties();
        try (InputStream input = TradeService.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }
            properties.load(input);
            threshold = Double.parseDouble(properties.getProperty("error.threshold"));
            if (threshold < 1 || threshold > 100) throw new InvalidThresholdValueException("Enter valid value");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Enter valid value");
        }
    }

    public static List<Trade> readCSVFile(String path, int error, int recordsCount, double thresholdValue) throws IOException,
            HitErrorsThresholdException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        reader.readLine();
        List<Trade> trades = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            recordsCount++;
            Trade trade = createTradeRecord(line);
            if (trade != null) {
                trades.add(trade);
            } else error++;
        }
        records = recordsCount;
        errorCount = error;
        double errorRate = ((double) error / recordsCount) * 100;
        if (errorRate > thresholdValue) {
            throw new HitErrorsThresholdException("File parsing failed...");
        }
        return trades;
    }

    public static Trade createTradeRecord(String line) {
        try {
            String[] tradeArray = line.split(",");
            return new Trade(tradeArray[0], tradeArray[1],
                    tradeArray[2], Integer.parseInt(tradeArray[3]),
                    Double.parseDouble(tradeArray[4]), LocalDate.parse(tradeArray[5]));
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
}
