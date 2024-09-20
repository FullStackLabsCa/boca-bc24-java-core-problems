package problems.tradeFileParser;

import problems.tradeFileParser.controller.TradeDOAImplementation;
import problems.tradeFileParser.controller.TradeDOA;
import problems.tradeFileParser.model.TradeModel;
import problems.tradeFileParser.reader.ThresholdReader;
import problems.tradeFileParser.reader.ThresholdReaderImplementation;
import problems.tradeFileParser.reader.TradeFileReader;
import problems.tradeFileParser.reader.TradeFileReaderImplementation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainRunner {
    public static List<TradeModel> tradeList = new ArrayList<>();
    public static double threshold = 0.0;

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);

        // Read threshold value first
        ThresholdReader thresholdReader = new ThresholdReaderImplementation();
        threshold = thresholdReader.readThreshold();

        System.out.print("Enter file path: ");
        String filePath = scanner.next();

        TradeFileReader tradeFileParser = new TradeFileReaderImplementation();
        List<TradeModel> tradeList = tradeFileParser.parseTradeFile(filePath);

        TradeDOA tradeDAO = new TradeDOAImplementation();
        tradeDAO.insertTrade(tradeList);

        scanner.close();
    }
}