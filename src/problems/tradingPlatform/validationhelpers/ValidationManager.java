package problems.tradingPlatform.validationhelpers;

import problems.tradingPlatform.helpers.ErrorManager;
import problems.tradingPlatform.models.Trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ValidationManager {

    static int rowVal = 0;
//    public static List<Trade> validData(String filePath, long errorThreshold) {
//        List<Trade> tradeList = new ArrayList<Trade>();
//        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
//            scanner.nextLine();
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] values = line.split(",");
//
//                if (values.length == 6) {
//                    int tradeId = Integer.parseInt(values[0]);
//                    String tradeIdentifier = values[1];
//                    String tickerSymbol = values[2];
//                    int quantity = Integer.parseInt(values[3]);
//                    double price = Double.parseDouble(values[4]);
//                    String tradeDate = values[5];
//                    Trade trade = new Trade(tradeId, tradeIdentifier, tickerSymbol, quantity, price, tradeDate);
//                    if (validateData(trade)) {
//                        tradeList.add(trade);
//                    }
//                }
//            }
//            scanner.close();
//            return  tradeList;
//        } catch (IOException e) {
//            errorCount++;
//            logError("\n IOException Occurred" + e.getMessage());
//        }
//        catch (HitErrorsThresholdException e) {
//            System.out.println("Validation Failed!.Error Count Increase the Maximum Threshold Count");
//        }
//        return tradeList;
//    }

        public static boolean validateData(Trade trade, int row)
        {
            rowVal = row;
            if (!isEmptyVal(String.valueOf(trade.getTradeId())) || !isEmptyVal(trade.getTickerSymbol())
                    || !isEmptyVal(String.valueOf(trade.getPrice()))
                    || !isEmptyVal(String.valueOf(trade.getQuantity()))
                    || !isEmptyVal(trade.getTradeDate())) {

                ErrorManager.checkForErrorThreshold("Value is Empty for : "+trade.getTradeId(),true,row);
                return  false;
            }

            if (!isValidIntValue(String.valueOf(trade.getQuantity()))) {
                ErrorManager.checkForErrorThreshold("Invalid int value For : "+trade.getTradeId(),true, row);
                return false;
            }
            if (!isValidDoubleValue(String.valueOf(trade.getPrice()))) {
                ErrorManager.checkForErrorThreshold("Invalid double value For : "+trade.getTradeId(),true, row);
                return  false;
            }
            if (!isValidDate(String.valueOf(trade.getTradeDate()))) {
                ErrorManager.checkForErrorThreshold("Invalid date value For : "+trade.getTradeId(),true, row);
                return  false;
            }
            return  true;
        }



    private static boolean isEmptyVal(String val)
     {
         return val != null && !val.isBlank();
     }


    private static boolean isValidIntValue(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            ErrorManager.checkForErrorThreshold("NumberFormatException For "+val,true, rowVal);
            return false;
        }
    }

    private static boolean isValidDoubleValue(String val) {
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            ErrorManager.checkForErrorThreshold("NumberFormatException For "+val,true, rowVal);
            return false;
        }
    }

    private static boolean isValidDate(String val) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(val);
            return true;
        } catch (ParseException e) {
            ErrorManager.checkForErrorThreshold("NumberFormatException For "+val,true, rowVal);
            return false;
        }
    }


}
