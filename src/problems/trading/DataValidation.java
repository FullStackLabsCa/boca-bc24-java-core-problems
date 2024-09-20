package problems.trading;


import problems.trading.services.TradingService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataValidation {
    public static boolean checkForAllValidations(String line) {
        String[] data = line.split(",");

            if( checkForValidNumberOfColumns(line) &&
            checkForValidQuantity(line) &&
            checkForValidPrice(line) &&
            checkForValidTradeDate(line)) {
                return true;
            }

//            checkForValidNumberOfColumns(line);
//            checkForValidQuantity(line);
//            checkForValidPrice(line);
//            checkForValidTradeDate(line);
//            return true;
        return false;
    }

    public static boolean checkForValidNumberOfColumns(String line) {

        //Validation - check for the correct number of columns
        String[] data = line.split(",");
        try{
            if(data.length != 6);
            return true;
        } catch (IllegalArgumentException i){
            System.out.println(data[0] + "Incorrect number of fields. Six fields are expected at line -- " +line);
        }
        TradingService.logReaderErrors(line + " Incorrect number of fields. Six fields are expected at line -- ");
//        if (data.length != 6) {
//            throw new IllegalArgumentException("Incorrect number of fields. Six fields are expected at line -- " +line);
        return false;
    }


    public static boolean checkForValidQuantity(String line) {
        //Validation - check if the value for quantity is always an integer
        String[] data = line.split(",");
        try {
            Integer.parseInt(data[3].trim());
            return true;
        } catch (NumberFormatException n) {
            TradingService.logReaderErrors(line);
            System.out.println(data[3].trim() + "is not a valid integer. Quantity should be an integer");
            return false;
        }

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
        TradingService.logReaderErrors(line);
        return false;
    }

    public static boolean checkForValidTradeDate(String line) {
        //Validation - check if the date in always in the format of "yyyy-MM-dd"
        String[] data = line.split(",");
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        try {
            LocalDate.parse(data[5].trim());
            return true;
        } catch (DateTimeException d) {
            System.out.println(data[5].trim() + "is not a valid date format. The correct date format is yyyy-MM-dd");
        }
        TradingService.logReaderErrors(line);
        return false;
    }

    public static boolean isEmpty(String line){
        //Check of there are any missing values
        String[] data = line.split(",");
        if(line == null){
            System.out.println("It is empty");
        }
        TradingService.logReaderErrors(line);
        return true;
    }

}