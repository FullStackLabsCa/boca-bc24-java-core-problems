package jdbc.trade.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateFileData {

    public static boolean isValidDateFormat(String dateString) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        if (!dateString.matches(regex)) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateInput(String rowLine, int lineNumber) {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/logs/read_error_log.txt";
        String row = "[" + rowLine + "] - ";
        String [] rowData = rowLine.split(",");
        if (rowData.length < 6) {
            WriteErrorLogs.writeErrorLogsToFile(row + "Row should contain atleast 6 data.", filePath, lineNumber);
            return false;
        }

        String tradeId = rowData[0].trim(), tradeIdentifier = rowData[1].trim(),
                tickerSymbol = rowData[2].trim(), trade_quantity = rowData[3].trim(),
                trade_price = rowData[4].trim(), tradeDate = rowData[5].trim();

        if (tradeId.isEmpty() || tradeIdentifier.isEmpty() || tickerSymbol.isEmpty() || trade_quantity.isEmpty() || trade_price.isEmpty() || tradeDate.isEmpty()) {
            WriteErrorLogs.writeErrorLogsToFile(row + "trade data should not be empty.", filePath, lineNumber);
            return false;
        }

        if (!tradeIdentifier.startsWith("TDB", 0)) {
            WriteErrorLogs.writeErrorLogsToFile(row + "trade identifier should start with prefix TDB.", filePath, lineNumber);
            return false;
        }

        if (tickerSymbol.length() > 5 || !tickerSymbol.matches("[a-zA-Z]+")) {
            System.out.println("!tickerSymbol.matches(\"[a-zA-Z]\")==" + !tickerSymbol.matches("[a-zA-Z]+"));
            WriteErrorLogs.writeErrorLogsToFile(row + "ticker symbol should be <=5 length and only alphabets.", filePath, lineNumber);
            return false;
        }

        if (!isValidDateFormat(tradeDate)) {
            WriteErrorLogs.writeErrorLogsToFile(row + "date should in 'yyyy-MM-dd' format.", filePath, lineNumber);
            return false;
        }

        if (!rowData[3].isEmpty()) {
            try {
                int quantity = Integer.parseInt(rowData[3]);
                if (quantity <= 0) {
                    WriteErrorLogs.writeErrorLogsToFile(row + "quantity should be > 0.", filePath, lineNumber);
                    return false;
                }
            } catch (NumberFormatException e) {
                WriteErrorLogs.writeErrorLogsToFile(row + "quantity is not a valid number.", filePath, lineNumber);
                return false;
            }
        }

        if (!rowData[4].isEmpty()) {
            try {
                double price = Double.parseDouble(rowData[4]);
                if (price <= 0) {
                    WriteErrorLogs.writeErrorLogsToFile(row + "price should be > 0.", filePath, lineNumber);
                    return false;
                }
            } catch (NumberFormatException e) {
                WriteErrorLogs.writeErrorLogsToFile(row + "price is not a valid number.", filePath, lineNumber);
                return false;
            }
        }

        return true;
    }
}
