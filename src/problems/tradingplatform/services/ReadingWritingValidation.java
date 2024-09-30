package problems.tradingplatform.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static problems.tradingplatform.services.TradingOperation.logError;

public class ReadingWritingValidation {
    static boolean isValidRow(String[] row, int currentRowNumber) {
        if (row.length != 6) {
            TradingOperation.errorCount++;
            logError("Read_Err | ", Arrays.toString(row), "Invalid number of columns", null);

            return false;
        }

        String trade_id = row[0];
        String trade_identifier = row[1];
        String ticker_symbol = row[2];
        String quantity = row[3];
        String price = row[4];
        String trade_date = row[5];

        if (!ReadingWritingValidation.isString(trade_id)) {
            TradingOperation.errorCount++;
            logError("Read_Err | ",Arrays.toString(row), "Invalid trade_id", currentRowNumber);
            return false;
        }

        if (!ReadingWritingValidation.isString(trade_identifier)) {
            TradingOperation.errorCount++;
            logError("Read_Err | ",Arrays.toString(row), "Invalid trade_identifier", currentRowNumber);

            return false;
        }
        if (!ReadingWritingValidation.isString(ticker_symbol)) {
            TradingOperation.errorCount++;
            logError("Read_Err | ",Arrays.toString(row), "Invalid ticker_symbol", currentRowNumber);
            return false;
        }

        if (!ReadingWritingValidation.isInteger(quantity)) {
            TradingOperation.errorCount++;
            logError("Read_Err | ",Arrays.toString(row), "Invalid quantity", currentRowNumber);
            return false;
        }

        if (!ReadingWritingValidation.isDouble(price)) {
            TradingOperation.errorCount++;
            logError("Read_Err | ",Arrays.toString(row), "Invalid price", currentRowNumber);
            return false;
        }

        if (!ReadingWritingValidation.isDate(trade_date, "yyyy-MM-dd")) {

            TradingOperation.errorCount++;
            logError("Read_Err | ",Arrays.toString(row), "Invalid trade_date format", currentRowNumber);
            return false;
        }
        return true;
    }

    public static boolean isString(String value) {

        return value != null && !value.trim().isEmpty();
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDate(String value, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
