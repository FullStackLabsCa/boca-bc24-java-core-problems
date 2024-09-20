package trading;

import trading_processing_without_object_creation.HitErrorsThresholdException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CSVTradeFileReader implements TradeFileReader {

    public static ArrayList<Trade> validTradeQue = new ArrayList<>();
    static double readerThreshold = 0;
    static double userThreshold = UserInteraction.userThreshold;
    static int totalEntries;

    @Override
    public List<Trade> tradeFileReader(File file) {

        int parsingSuccess = 0, parsingFailure = 0, line_no = 0;
        Trade trade = null;
        Trade tradeForLine = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            bufferedReader.readLine();// to skip first header line
            String currentLine = bufferedReader.readLine();
            totalEntries = countEntries(file);
            System.out.println("Total number of Entries in a file is " + totalEntries);
            FIRST:
            while (currentLine != null) {
                String currentTransaction = currentLine;
                String[] splitTransaction = currentTransaction.split(",|\n");
                try {
                    line_no++;
                    tradeForLine = new Trade(line_no);
                    boolean validatedFieldsInTradeLine = TradeFieldValidator.validateFieldsInTradeLine(splitTransaction);
                    if (validatedFieldsInTradeLine) {
                        String trade_id = splitTransaction[0];
                        String trade_identifier = splitTransaction[1];
                        String ticker_symbol = splitTransaction[2];
                        int quantity = Integer.parseInt(splitTransaction[3]);
                        double price = Double.parseDouble(splitTransaction[4]);
                        String date = splitTransaction[5];
                        LocalDate parseDate = LocalDate.parse(date);
                        trade = new Trade(line_no, trade_id, trade_identifier, ticker_symbol, quantity, price, parseDate);
                        validTradeQue.add(trade);
                        parsingSuccess++;
                    }
                } catch (IllegalArgumentException e) {

                    if (tradeForLine != null) {
                        LogFileWriter.writeLog("Error in Line no -> " + tradeForLine.getLine_no() + " " + e.getMessage() + " -> " + currentTransaction, "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/Reader_error_log.txt");
                    }
                    parsingFailure++;
                    if (totalEntries > 0) {
                        readerThreshold = (parsingFailure / totalEntries) * 100;
                        if (readerThreshold >= userThreshold) {
                            throw new HitErrorsThresholdException("Threshold Reached While adding to array....");
                        }
                    } else throw new ArithmeticException("File Doesn't have any Entry");
                }
                currentLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total number of Entries pass Parsing Test " + parsingSuccess);
        System.out.println("Total number of Entries fail Parsing Test " + parsingFailure);
        return validTradeQue;
    }

    public static int countEntries(File file) {
        int totalEntries = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            bufferedReader.readLine(); // To Skip Header Line
            while (bufferedReader.readLine() != null) {
                totalEntries++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return totalEntries;
    }

}