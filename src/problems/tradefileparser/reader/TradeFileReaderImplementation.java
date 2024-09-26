package problems.tradefileparser.reader;

import problems.tradefileparser.exceptions.ParseingThresholdException;
import problems.tradefileparser.model.TradeModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static problems.tradefileparser.MainRunner.threshold;
import static problems.tradefileparser.MainRunner.tradeList;


public class TradeFileReaderImplementation implements TradeFileReader {
    @Override
    public List<TradeModel> parseTradeFile(String filePath) {
        int countTry = 0;
        int countSuccess = 0;
        int totalrows = 0;

        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            scanner.useDelimiter("\n|,");

            while (scanner.hasNext()) {
                try {
                    totalrows++;
                    String trade_id = scanner.next();
                    String trade_identifier = scanner.next();
                    String ticker_symbol = scanner.next();
                    int quantity = scanner.nextInt();
                    double price = scanner.nextDouble();
                    String trade_date = scanner.next();

                    TradeModel tradeModel = new TradeModel(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date);
                    tradeList.add(tradeModel);
                    countSuccess++;
                } catch (InputMismatchException e) {
                    countTry++;
                    System.err.println("Error at line: " + totalrows + " >>> " + scanner.next());
                    e.printStackTrace();
                    if (scanner.hasNext()) {
                        scanner.nextLine();
                    }
                    double incorrectRows = (((double) countTry / totalrows) * 100);
                    if (incorrectRows > threshold) {
                        throw new ParseingThresholdException("Threshold reached max number of incorrect rows");
                    }
                }
            }
        } catch (FileNotFoundException | ParseingThresholdException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------------------------------After parsing-------------------------------");
        System.out.println("Failed rows: " + countTry);
        System.out.println("Success rows: " + countSuccess);
        System.out.println("Total rows read: " + totalrows);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
        return tradeList;
    }
}
