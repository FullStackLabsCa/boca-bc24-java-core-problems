package tradefileprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradeFileReader implements tradefileprocessing.FileReader {
    private static int listSize;
    private static int invalidListSize;
    private static double threshold = Double.parseDouble(PropertiesLoader.getProperty("app.file.threshold"));


    @Override
    public List<String[]> readFile(String filePath) throws IOException {
        String line;
        String splitBy = ",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            List<String[]> listOfTrades = new ArrayList<>();
            bufferedReader.readLine(); // Skip header
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(splitBy);
                listOfTrades.add(data);
            }
            return listOfTrades;
        }
    }

    @Override
    public List<String[]> filterData(List<String[]> list) {
        List<String[]> invalidTrades = new ArrayList<>();
        for (String[] trade : list) {
            try {
                String tradeId = trade[0];
                String tradeIdentifier = trade[1];
                String tickerSymbol = trade[2];
                int quantity = Integer.parseInt(trade[3]);
                double price = Double.parseDouble(trade[4]);
                Date tradeDate = Date.valueOf(trade[5]);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid trade format: " + Arrays.toString(trade));
                invalidTrades.add(trade);
            }
        }
        list.removeAll(invalidTrades);
        listSize = list.size();
        invalidListSize = invalidTrades.size();
        threshold = (25 / 100.0) * listSize;
        System.out.println("\nList of valid trades:");
        list.forEach(trade -> System.out.println(Arrays.toString(trade)));
        System.out.println("Invalid rows from file: ");
        invalidTrades.forEach(trade -> System.out.println(Arrays.toString(trade)));
        return list;
    }

    public static int getInvalidListSize() {
        return invalidListSize;
    }

    public static double getThreshold() {
        return threshold;
    }
}
