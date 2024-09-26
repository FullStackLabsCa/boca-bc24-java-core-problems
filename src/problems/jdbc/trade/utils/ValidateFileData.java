package jdbc.trade.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class ValidateFileData {

    private ValidateFileData() {}

    public static boolean isValidDateFormat(String dateString) {
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

        if (!dateString.matches(regex)) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            return dateString.equals(formatter.format(date));
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateInput(String rowLine, int lineNumber) {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/logs/read_error_log.txt";
        String row = "[" + rowLine + "] - ";
        String[] rowData = rowLine.split(",");

        if (!isValidRowData(rowData, row, lineNumber, filePath)) {
            return false;
        }

        return validateTradeData(row, lineNumber, filePath, rowData);
    }

    private static boolean isValidRowData(String[] rowData, String row, int lineNumber, String filePath) {
        if (rowData.length < 6) {
            WriteErrorLogs.writeErrorLogsToFile(row + "Row should contain at least 6 data.", filePath, lineNumber);
            return false;
        }

        for (String data : rowData) {
            if (data.trim().isEmpty()) {
                WriteErrorLogs.writeErrorLogsToFile(row + "trade data should not be empty.", filePath, lineNumber);
                return false;
            }
        }

        return true;
    }

    private static boolean validateTradeData(String row, int lineNumber, String filePath, String [] rowData) {
        String tradeIdentifier = rowData[1].trim();
        String tickerSymbol = rowData[2].trim();
        String tradeQuantity = rowData[3].trim();
        String tradePrice = rowData[4].trim();
        String tradeDate = rowData[5].trim();

        if (!tradeIdentifier.startsWith("TDB")) {
            WriteErrorLogs.writeErrorLogsToFile(row + "trade identifier should start with prefix TDB.", filePath, lineNumber);
            return false;
        }

        if (tickerSymbol.length() > 5 || !tickerSymbol.matches("[a-zA-Z]+")) {
            WriteErrorLogs.writeErrorLogsToFile(row + "ticker symbol should be <=5 length and only alphabets.", filePath, lineNumber);
            return false;
        }

        if (!isValidDateFormat(tradeDate)) {
            WriteErrorLogs.writeErrorLogsToFile(row + "date should be in 'yyyy-MM-dd' format.", filePath, lineNumber);
            return false;
        }

        return validateQuantity(row, lineNumber, filePath, tradeQuantity) && validatePrice(row, lineNumber, filePath, tradePrice);
    }

    private static boolean validateQuantity(String row, int lineNumber, String filePath, String tradeQuantity) {
        if (!tradeQuantity.isEmpty()) {
            try {
                int quantity = Integer.parseInt(tradeQuantity);
                if (quantity <= 0) {
                    WriteErrorLogs.writeErrorLogsToFile(row + "quantity should be > 0.", filePath, lineNumber);
                    return false;
                }
            } catch (NumberFormatException e) {
                WriteErrorLogs.writeErrorLogsToFile(row + "quantity is not a valid number.", filePath, lineNumber);
                return false;
            }
        }
        return true;
    }

    private static boolean validatePrice(String row, int lineNumber, String filePath, String tradePrice) {
        if (!tradePrice.isEmpty()) {
            try {
                double price = Double.parseDouble(tradePrice);
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
