package problems.tradefileparser;

import problems.tradefileparser.controller.TradeDOAImplementation;
import problems.tradefileparser.controller.TradeDOA;
import problems.tradefileparser.databaseconnection.HikariCP;
import problems.tradefileparser.model.TradeModel;
import problems.tradefileparser.reader.ThresholdReader;
import problems.tradefileparser.reader.ThresholdReaderImplementation;
import problems.tradefileparser.reader.TradeFileReader;
import problems.tradefileparser.reader.TradeFileReaderImplementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainRunner {
    public static List<TradeModel> tradeList = new ArrayList<>();
    public static double threshold = 0.0;

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);

        ThresholdReader thresholdReader = new ThresholdReaderImplementation();
        threshold = thresholdReader.readThreshold();

        System.out.print("Enter file path: ");
        String filePath = scanner.next();

        TradeFileReader tradeFileParser = new TradeFileReaderImplementation();
        List<TradeModel> tradeList = tradeFileParser.parseTradeFile(filePath);

        try (Connection connection = HikariCP.getConnection()) {
            TradeDOA tradeDAO = new TradeDOAImplementation(tradeList, connection);
            tradeDAO.insertTrade();

            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}