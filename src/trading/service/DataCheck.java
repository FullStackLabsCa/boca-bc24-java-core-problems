package trading.service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DataCheck {
    private DataCheck(){

    }
    static boolean checkForValidTradeIdentifier(String tradeIdentifier, int lineIndex, List<String> errorMessages) {
        String regex = "TDB_\\d+";
        if (!tradeIdentifier.matches(regex)) {
            errorMessages.add(tradeIdentifier + " is not a valid trade identifier in line " + lineIndex);
            return false;
        }
        return true;
    }

    static boolean checkForValidTradeDate(String tradeDate, int lineIndex, List<String> errorMessages) {
        try {
            LocalDate date = LocalDate.parse(tradeDate.trim());
            if (date.isAfter(LocalDate.now())) {
                errorMessages.add("Trade date cannot be in the future in line " + lineIndex);
                return false;
            }
        } catch (DateTimeParseException e) {
            errorMessages.add("Invalid date format in line " + lineIndex);
            return false;
        }
        return true;
    }

    static boolean checkForValidPrice(String priceStr, int lineIndex, List<String> errorMessages) {
        try {
            double price = Double.parseDouble(priceStr.trim());
            if (price < 0) {
                errorMessages.add("Price cannot be negative in line " + lineIndex);
                return false;
            }
        } catch (NumberFormatException e) {
            errorMessages.add(priceStr + " is not a valid number for price in line " + lineIndex);
            return false;
        }
        return true;
    }

    static boolean checkForValidQuantity(String quantityStr, int lineIndex, List<String> errorMessages) {
        try {
            int quantity = Integer.parseInt(quantityStr.trim());
            if (quantity < 0) {
                errorMessages.add("Quantity cannot be negative in line " + lineIndex);
                return false;
            }
        } catch (NumberFormatException e) {
            errorMessages.add(quantityStr + " is not a valid integer for quantity in line " + lineIndex);
            return false;
        }
        return true;
    }
}
