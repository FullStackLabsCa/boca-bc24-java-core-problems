package problems.trade.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeValidator {

    public static boolean isValidTradeId(String tradeId) {
        return tradeId != null && tradeId.matches("^TDB_.*");
    }

    public static boolean isValidQuantity(String quantity) {
        boolean isvalid = false;
        try {
            if (Integer.parseInt(quantity) > 0) {
                isvalid = true;
            }
        } catch (NumberFormatException e) {

        }
        return isvalid;
    }

    public static boolean isValidPrice(String price) {
        boolean isvalid = false;
        try {
            if (Double.parseDouble(price) > 0) {
                isvalid = true;
            }
        } catch (NumberFormatException e) {

        }
        return isvalid;
    }

    public static boolean isValidTickerSymbol(String tickerSymbol) {
        return tickerSymbol != null && tickerSymbol.matches("^[A-Z]{1,5}$");
    }

    public static boolean isValidTradeDate(String tradeDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat.parse(tradeDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
