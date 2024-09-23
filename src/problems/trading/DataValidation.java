package problems.trading;


import problems.trading.services.TradingService;
import problems.trading.repository.TradingRepository;
import problems.trading.TradingProcessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static problems.trading.TradingProcessor.userThreshold;
import static problems.trading.TradingProcessor.filePath;

public class DataValidation {
    public static boolean checkForAllValidations(String line, Connection connection) {
        String[] data = line.split(",");

            if( checkForValidNumberOfColumns(line) &&
            checkForValidQuantity(line) &&
            checkForValidTickerSymbol(line,connection) &&
            checkForValidPrice(line) &&
            checkForValidTradeDate(line)){
                return true;
            }

//            checkForValidNumberOfColumns(line);
//            checkForValidQuantity(line);
//            checkForValidPrice(line);
//            checkForValidTradeDate(line);
//            return true;
        return false;
    }


    public static String checkForCorrectFilePath(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            //String file;

        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path provided");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

        public static boolean checkForValidNumberOfColumns(String line) {

        //Validation - check for the correct number of columns

        String[] data = line.split(",");
        try{
            if(data.length != 6) {
                System.out.println("data.length = " + data.length + " ? " + (data.length != 6));
                System.out.println(data[0] + " Incorrect number of fields. Six fields are expected at line -- " + line);
                TradingService.logReaderErrors(line + " Incorrect number of fields. Six fields are expected at line -- ");
            }
            return true;
        } catch (IllegalArgumentException i){
            System.out.println(data[0] + " Incorrect number of fields. Six fields are expected at line -- " + line);
        }
        return false;
    }


    public static boolean checkForValidQuantity(String line) {
        //Validation - check if the value for quantity is always an integer
        String[] data = line.split(",");
        try {
            Integer.parseInt(data[3].trim());
            return true;

        } catch (NumberFormatException n) {
            TradingService.logReaderErrors(line + " -- Quantity should be an integer.");
            System.out.println(data[3].trim() + " -- Quantity should be an integer");
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
            TradingService.logReaderErrors(line + " -- Price needs to be a decimal");
            System.out.println(data[4].trim() + " -- Price needs to be a decimal");
        }

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
            TradingService.logReaderErrors(line + " -- not a valid date format. The correct date format is yyyy-MM-dd");
            System.out.println(data[5].trim() + " -- not a valid date format. The correct date format is yyyy-MM-dd");
        }

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

public static boolean isTickerSymbolValid(Connection connection, String tickerSymbol){
        return  TradingRepository.isTickerSymbolValid(connection,tickerSymbol);
}
 public static boolean checkForValidTickerSymbol(String line, Connection connection){
        String[] data = line.split(",");
        String tickerSymbol = data[2].trim();

        if(!isTickerSymbolValid(connection, tickerSymbol)){
            System.out.println("Invalid security ticker symbol");
            TradingService.logWritingErrors(line + " -- Invalid ticker symbol");
        }
        return true;
 }

 public static double checkForValidThresholdFromUser(double userThreshold){
        if(userThreshold > 1 && userThreshold < 100){
            return userThreshold;
        } else {
            System.out.println(" The threshold value should be between 0 and 100. Please enter the value again.");
        }
     return 0;
 }
}

