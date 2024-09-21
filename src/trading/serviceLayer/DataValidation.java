package trading.serviceLayer;

import trading.RepositoryLayer.TradingRep;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//public class DataValidation {
//    public static boolean checkForValidTradeDate(String line) throws IOException {
//        //Validation - check if the date in always in the format of "yyyy-MM-dd"
//        String[] data = line.split(",");
//        String datePattern = "yyyy-MM-dd";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
//        try {
//            LocalDate.parse(data[5].trim());
//            return true;
//        } catch (DateTimeException d) {
//            System.out.println(data[5].trim() + " is not a valid date format. The correct date format is yyyy-MM-dd");
//        }
//        TradingRep.errorLog(line);
//        return false;
//    }

//    public  static boolean checkForValidTradeDate(String line) throws IOException {
//        String[] data = line.split(",");
//        if (data.length <= 5 || data[5].trim().isEmpty()) {
//            System.out.println("Date field is missing or empty.");
//            TradingRep.errorLog(line);
//            return false;
//        }
//
//        String dateStr = data[5].trim();
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        // Check if date matches the expected format and is valid
//        try {
//            LocalDate.parse(dateStr, dateFormatter);
//            return true; // Valid date
//        } catch (DateTimeParseException e) {
//            System.out.println(dateStr + " is not a valid date format. The correct format is yyyy-MM-dd.");
//            TradingRep.errorLog(line);
//            return false; // Invalid date
//        }


    public class DataValidation {
        public static boolean checkForValidTradeDate(String line) {
            String[] fields = line.split(",");
            if (fields.length < 6) return true; // If not enough fields, consider it invalid.

            String dateField = fields[5].trim();
            if (dateField.isEmpty()) return true; // Date is missing or empty.

            try {
                LocalDate.parse(dateField); // Attempt to parse the date.
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format");
                return true; // If parsing fails, consider it invalid.
            }

            return false; // Valid date, return false for no errors.
        }

        public static boolean checkForValidQuantity(String line) throws IOException {
            //Validation - check if the value for quantity is always an integer
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
