package problems.tradeOperations.oldFiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TradeValidator {

    // Validation - Trade ID
    public boolean isValidTradeId (String tradeId){
        return tradeId != null && !tradeId.trim().isEmpty();
    }

    // Validation - Ticker Symbol
    public boolean isValidTickerSymbol (String tickerSymbol){
        return tickerSymbol != null && !tickerSymbol.trim().isEmpty();
    }

    // Validation - Quantity
    public boolean isValidQuantity (String quantity){
        try {
            Integer.parseInt(quantity);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validation - Price
    public boolean isValidPrice (String price){
        try {
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    // Validation - Price
    public boolean isValidTradeDate (String tradeDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // strict date validation
        try {
            sdf.parse(tradeDate);
            return true;
        } catch (ParseException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }


}
