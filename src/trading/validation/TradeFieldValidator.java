package trading.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TradeFieldValidator {

    public static boolean validateFieldsInTradeLine(String[] fields) {

        if (fields[0].isEmpty()) {
            try {
                fields[1].isEmpty();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("trade entry is empty");
            }
            throw new IllegalArgumentException("trade entry is empty");
        }
        // To display message in log file for trade_id
        try {
            if (fields[0] == null || fields[0].isEmpty()) {
                throw new IllegalArgumentException("Error in trade_id -> " + fields[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("trade_id is Missing");
        }

        // To display message in log file for trade_identifier
        try {
            if (fields[1] == null || fields[1].isEmpty()) {
                throw new IllegalArgumentException("Error in trade_identifier -> " + fields[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("trade_identifier is Missing");
        }

        // To display message in log file for trade_symbol
        try {
            if (fields[2] == null || fields[2].isEmpty()) {
                throw new IllegalArgumentException("Error in trade_symbol -> " + fields[2]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("trade_symbol is Missing");
        }

        // To display message in log file for quantity
        try {
            if (fields[3].isEmpty()) {
                throw new IllegalArgumentException("Error in quantitiy -> " + fields[3]);
            }
            int parseInt = Integer.parseInt(fields[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("quantities is Missing");
        }

        // To display message in log file for price
        try {
            if (fields[4].isEmpty()) {
                throw new IllegalArgumentException("Error in price -> " + fields[4]);
            }
            double parseInt = Double.parseDouble(fields[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("price is Missing");
        }
//TO check is there any field is missing
        if (fields.length != 6) {
            throw new IllegalArgumentException("Invalid number of fields in line");
        }

        // To display message in log file for Date
        try {
            if (fields[5].isEmpty()) {
                throw new IllegalArgumentException("Error in date -> " + fields[5]);
            }
            LocalDate parse = LocalDate.parse(fields[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Date is Missing");
        } catch (DateTimeParseException s) {
            throw new IllegalArgumentException("Date Format is Incorrect");
        }


        return true;
    }
}