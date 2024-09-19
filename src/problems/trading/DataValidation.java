package problems.trading;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DataValidation {
    public static boolean checkForAllValidations(String line) throws ParseException {
        String[] data = line.split(",");
        checkForValidNumberOfColumns(line);
        checkForValidQuantity(line);
        checkForValidPrice(line);
        checkForValidTradeDate(line);
        return false;
    }


    public static boolean checkForValidNumberOfColumns(String line) {

        //Validation - check for the correct number of columns
        String[] data = line.split(",");
        if (data.length != 6) {
            System.out.println("Incorrect number of fields. Six fields are expected" + line);
            //continue;
            return false;
        }
        return true;
    }

    public static boolean checkForValidQuantity(String line) {
        //Validation - check if the value for quantity is always an integer
        String[] data = line.split(",");
        try {
            Integer.parseInt(data[3].trim());
            return true;
        } catch (NumberFormatException n) {
            System.out.println(data[3].trim() + "is not a valid integer. Quantity should be an integer");
        }
        return false;
    }

    public static boolean checkForValidPrice(String line) {
        //Validation - check if the value for price is always a double (decimal)
        String[] data = line.split(",");
        try {
            Double.parseDouble(data[4].trim());
            return true;
        } catch (NumberFormatException e) {
            System.out.println(data[4].trim() + "is not a valid decimal price. Price needs to be a decimal");

        }
        return false;
    }

    public static  boolean checkForValidTradeDate(String line) throws ParseException {
        //Validation - check if the date in always in the format of "yyyy-MM-dd"
        String[] data = line.split(",");
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        LocalDate.parse(data[5].trim());
        return true;
    }
}