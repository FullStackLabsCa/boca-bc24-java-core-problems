package thread.tradeprocess.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class ValidateFileData {

    private ValidateFileData() {}

//    public static boolean isValidDateTimeFormat(String dateTimeString) {
//        System.out.println("dateString=" + dateTimeString);
//        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) \\d{2}:\\d{2}:\\d{2}$";
//
//        if (!dateTimeString.matches(regex)) {
//            return false;
//        }
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        try {
//            LocalDate dateTime = LocalDate.parse(dateTimeString, formatter);
//            return dateTimeString.equals(formatter.format(dateTime));
//        } catch (DateTimeParseException e) {
//            return false;
//        }
//    }

    public static boolean validateFileRow(String rowLine) {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/thread/tradeprocess/logs/read_error_log.txt";
        String row = "[" + rowLine + "] - ";
        String[] rowData = rowLine.split(",");

        if (!isValidRowData(rowData, row, filePath)) {
            return false;
        }

        return validateTradeData(row, filePath, rowData);
    }

    private static boolean isValidRowData(String[] rowData, String row, String filePath) {
        if (rowData.length < 7) {
            WriteErrorLogs.writeErrorLogsToFile(row + "Row should contain at least 7 data.", filePath);
            return false;
        }

        for (String data : rowData) {
            if (data.trim().isEmpty()) {
                WriteErrorLogs.writeErrorLogsToFile(row + "trade data should not be empty.", filePath);
                return false;
            }
        }

        return true;
    }

    private static Boolean validateTradeData(String row, String filePath, String [] rowData) {
        String tradeId = rowData[0].trim();
        String transactionTime = rowData[1].trim();
        String accountNumber = rowData[2].trim();
        String cusip = rowData[3].trim();
        String activity = rowData[4].trim();
        String tradeQuantity = rowData[5].trim();
        String tradePrice = rowData[6].trim();

        if (!tradeId.startsWith("TDB")) {
            WriteErrorLogs.writeErrorLogsToFile(row + "trade identifier should start with prefix TDB.", filePath);
            return false;
        }

        if (cusip.length() > 5 || !cusip.matches("[a-zA-Z]+")) {
            WriteErrorLogs.writeErrorLogsToFile(row + "ticker symbol should be <=5 length and only alphabets.", filePath);
            return false;
        }

        if (!accountNumber.startsWith("TDB_CUST")) {
            WriteErrorLogs.writeErrorLogsToFile(row + "trade account number should start with prefix TDB_CUST.", filePath);
            return false;
        }

        if (!activity.matches("SELL|BUY")) {
            WriteErrorLogs.writeErrorLogsToFile(row + "Trade activity should be either buy or sell.", filePath);
            return false;
        }

//        if (!isValidDateTimeFormat(transactionTime)) {
//            WriteErrorLogs.writeErrorLogsToFile(row + "date should be in 'yyyy-MM-dd HH:mm:ss ' format.", filePath);
//            return false;
//        }

        return validateQuantity(row, filePath, tradeQuantity) && validatePrice(row, filePath, tradePrice);
    }

    private static boolean validateQuantity(String row, String filePath, String tradeQuantity) {
        if (!tradeQuantity.isEmpty()) {
            try {
                int quantity = Integer.parseInt(tradeQuantity);
                if (quantity <= 0) {
                    WriteErrorLogs.writeErrorLogsToFile(row + "quantity should be > 0.", filePath);
                    return false;
                }
            } catch (NumberFormatException e) {
                WriteErrorLogs.writeErrorLogsToFile(row + "quantity is not a valid number.", filePath);
                return false;
            }
        }
        return true;
    }

    private static boolean validatePrice(String row, String filePath, String tradePrice) {
        if (!tradePrice.isEmpty()) {
            try {
                double price = Double.parseDouble(tradePrice);
                if (price <= 0) {
                    WriteErrorLogs.writeErrorLogsToFile(row + "price should be > 0.", filePath);
                    return false;
                }
            } catch (NumberFormatException e) {
                WriteErrorLogs.writeErrorLogsToFile(row + "price is not a valid number.", filePath);
                return false;
            }
        }
        return true;
    }
}
