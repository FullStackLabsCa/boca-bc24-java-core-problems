package problems.trading_3way_with_object.utility;

import problems.trading_3way_with_object.Trade;
import problems.trading_3way_with_object.contract.TradeReader;
import problems.trading_3way_with_object.exception.HitErrorsThresholdException;


import java.io.*;
import java.util.*;


public class CsvTradeReader implements TradeReader{
//    public static Double userThresholdInput = 50.0;
    public static int lineCount = 0;  //to find the actual line number of trade then subtract it by 1 because we have header
    public static int errorReaderCounter = 0;
    public static int successfullyParsedCounter = 0;
    public static List<String> errorReaderMessageList = new ArrayList<>();

    @Override
    public List<Trade> readTrades(String filePath, double userThresholdInput) throws Exception {
        List<Trade> trades = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(
                     "/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/problems/trading_3way_with_object/files/file_error_log.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 1) continue; // Skip header

                try {
                    String[] fields = line.split(",");
                    TradeFieldValidator.validateAndParseTradeLine(fields);
                    Trade trade = new Trade(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]), fields[5]);
                    trades.add(trade);
                    successfullyParsedCounter++;
                } catch (Exception e) {
                    String errorReaderMessage = "Line # " + (lineCount-1) + ": " + line +" Failed for reason: " + e.getMessage();
                    errorWriter.write(errorReaderMessage);
                    errorWriter.newLine();
                    errorReaderMessageList.add(errorReaderMessage);
                    errorReaderCounter++;
                }
            }
        }

        double errorPercentage = (double) errorReaderCounter / (lineCount - 1) * 100;
        if (errorPercentage > userThresholdInput) {
            throw new HitErrorsThresholdException("File validation error exceeds threshold!");
        }

        return trades;
    }



}
