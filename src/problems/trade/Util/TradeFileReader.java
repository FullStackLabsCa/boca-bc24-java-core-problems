package problems.trade.Util;

import problems.trade.exceptions.HitErrorsThresholdException;
import problems.trade.interfaces.TradeFileReadInterface;
import problems.trade.model.Trade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static problems.trade.TradeRunner.invalidTradeList;
import static problems.trade.TradeRunner.tradeThreshold;
import static problems.trade.Util.CreateErrorTradefile.createErrorFile;
import static problems.trade.Util.TradeValidator.*;

public class TradeFileReader implements TradeFileReadInterface {
    public List<Trade> validtradeList = new ArrayList<>();

    @Override
    public List<Trade> readTradeDataFromCSV(String filePath) throws HitErrorsThresholdException {

        System.out.println(filePath);
        try {
            File file = new File(filePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        if (validateTradeLine(line)) {
                            String[] data = line.split(",");
                            Trade trade = new Trade(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), getFormattedDate(data[5]));
                            validtradeList.add(trade);
                        }
                    }
                    if (!invalidTradeList.isEmpty()) {
                        createErrorFile(invalidTradeList, "ReadError");
                    }
                    System.out.println("Trade List size :"+validtradeList.size());
                } catch (IOException e) {
                    System.out.println("IO Exception !!" + e.getMessage());
                }
            } else {
                System.out.println("File path not found retry");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(checkThreshold()){
            throw new HitErrorsThresholdException("Hit Error Threshold Exceeded !!");
        }
        return validtradeList;
    }
    public  boolean checkThreshold() {
        int totalTradeCount = invalidTradeList.size() + validtradeList.size();
        double percentageExecuted = ((double) invalidTradeList.size() / totalTradeCount) * 100;
        return percentageExecuted > tradeThreshold ;
    }

    public boolean validateTradeLine(String line) {
        String[] data = line.split(",");
        if (data.length != 6) {
            invalidTradeList.add(line + " - Expected 6 fields.");
            return false;
        }
        return validateTradeData(data);
    }

    private boolean validateTradeData(String[] data) {
        List<String> errorMessages = new ArrayList<>();
        String tradeId = data[0];
        String tradeIdentifier = data[1];
        String tickerSymbol = data[2];
        String quantity = data[3];
        String price = data[4];
        String tradeDate = data[5];

        if (!isValidTradeId(tradeIdentifier)) {
            errorMessages.add("Invalid Trade ID");
        } else if (!isValidQuantity(quantity)) {
            errorMessages.add("Invalid Trade Quantity! should be greater than '0'");
        } else if (!isValidPrice(price)) {
            errorMessages.add("Invalid Trade Price");
        } else if (!isValidTickerSymbol(tickerSymbol)) {
            errorMessages.add("Invalid Trade Ticker symbol");
        } else if (!isValidTradeDate(tradeDate)) {
            errorMessages.add("Invalid Trade Date");
        }

        if (!errorMessages.isEmpty()) {
//            invalidTradeList.forEach(System.out::println);
            invalidTradeList.add(Arrays.toString(data) + " - " + String.join(", ", errorMessages));
            return false;
        }
        return true;
    }

    private java.sql.Date getFormattedDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date utilDate = sdf.parse(date);
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return new java.sql.Date(sdf.parse(date).getTime());

    }
}
