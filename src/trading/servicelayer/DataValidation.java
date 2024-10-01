package trading.servicelayer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@SuppressWarnings("squid:S106")
public class DataValidation {

    private DataValidation() {
    }

    public static boolean checkForValidTradeDate(String line) {
            String[] fields = line.split(",");
            if (fields.length < 6) return true;

            String dateField = fields[5].trim();
            if (dateField.isEmpty()) return true;
            try {
                LocalDate.parse(dateField);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format");
                return true;
            }
            return false;
        }
}