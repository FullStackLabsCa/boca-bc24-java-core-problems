package Trading.serviceLayer;

import Trading.repoLayer.TradingRep;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DataValidation {
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

        public static boolean checkForValidQuantity(String line) throws IOException {
            String[] data = line.split(",");
            try {
                Integer.parseInt(data[3].trim());
                return true;
            } catch (NumberFormatException n) {
                TradingRep.errorLog(line);
                System.out.println(data[3].trim() + "is not a valid integer. Quantity should be an integer");
                return false;
            }
        }
    }