package problems.trading;


import problems.trading.services.TradingService;
import problems.trading.repository.TradingRepository;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DataValidation {
    public static boolean checkForAllValidations(String line, Connection connection, double lineNumber) {
        String[] data = line.split(",");

        if (checkForValidNumberOfColumns(line, lineNumber) &&
                checkForValidQuantity(line, lineNumber) &&
                checkForValidTickerSymbol(line, connection, lineNumber) &&
                checkForValidPrice(line, lineNumber) &&
                checkForValidTradeDate(line, lineNumber)) {
            return true;
        }

        return false;
    }


    public static boolean checkForValidNumberOfColumns(String line, double lineNumber) {

        //Validation - check for the correct number of columns

        String[] data = line.split(",");
        try {
            if (data.length != 6) {
//                System.out.println("data.length = " + data.length + " ? " + (data.length != 6));
                System.out.println(data[0] + " -- Incorrect number of fields. Six fields are expected at line -- " + line);
                TradingService.logReaderErrors("#" + lineNumber + "  -- Error line -- {" + line + "} -- Incorrect number of fields. Six fields are expected.");
            }
            return true;
        } catch (IllegalArgumentException i) {
            System.out.println(data[0] + " Incorrect number of fields. Six fields are expected at line -- " + line);
        }
        return false;
    }


    public static boolean checkForValidQuantity(String line, double lineNumber) {
        //Validation - check if the value for quantity is always an integer
        String[] data = line.split(",");
        try {
            Integer.parseInt(data[3].trim());
            return true;

        } catch (NumberFormatException n) {
            TradingService.logReaderErrors("#" + lineNumber + " -- Error line --{" + line + "} -- Place of error: [" + data[3].trim() + "] -- Quantity should be an integer.");
            System.out.println(data[3].trim() + " -- Quantity should be an integer");
            return false;
        }

    }

    public static boolean checkForValidPrice(String line, double lineNumber) {
        //Validation - check if the value for price is always a double (decimal)
        String[] data = line.split(",");
        try {
            Double.parseDouble(data[4].trim());
            return true;
        } catch (NumberFormatException e) {
            TradingService.logReaderErrors("#" + lineNumber + " --Error line --{" + line + "} -- Place of error: [" + data[4].trim() + "] -- Price should be in decimal");
            System.out.println(data[4].trim() + " -- Price needs to be a decimal");
        }

        return false;
    }

    public static boolean checkForValidTradeDate(String line, double lineNumber) {
        //Validation - check if the date in always in the format of "yyyy-MM-dd"
        String[] data = line.split(",");
        String datePattern = "yyyy-MM-dd";
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        if (!line.matches(regex)) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        try {
            LocalDate.parse(data[5].trim());
            return true;
        } catch (DateTimeParseException d) {
            TradingService.logReaderErrors("#" + lineNumber + " -- Error line -- {" + line + "} --Place of error: [" + data[5].trim() + "] --- not a valid date format. The correct date format is yyyy-MM-dd");
            System.out.println(data[5].trim() + " -- not a valid date format. The correct date format is yyyy-MM-dd");
        }

        return false;
    }


    public static boolean isTickerSymbolValid(Connection connection, String tickerSymbol) {
        return TradingRepository.isTickerSymbolValid(connection, tickerSymbol);
    }

    public static boolean checkForValidTickerSymbol(String line, Connection connection, double lineNumber) {
        String[] data = line.split(",");
        String tickerSymbol = data[2].trim();



        if (!isTickerSymbolValid(connection, tickerSymbol)) {
            System.out.println("Invalid security ticker symbol");
            TradingService.logWritingErrors("#" + lineNumber + " -- Error line -- {" + line + "} -- Place of error: [" + data[2].trim() + "] -- Invalid ticker symbol");
        }
        return true;
    }
}
