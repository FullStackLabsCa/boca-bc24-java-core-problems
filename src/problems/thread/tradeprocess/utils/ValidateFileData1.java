package thread.tradeprocess.utils;

public final class ValidateFileData1 {

    private ValidateFileData1() {}

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

    public static String validateFileRow(String rowLine) {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/thread/tradeprocess/logs/read_error_log.txt";
        String row = "[" + rowLine + "] - ";
        String[] rowData = rowLine.split(",");

        if (isValidRowData(rowData, row, filePath) == null) {
            return null;
        }

        return validateTradeData(row, filePath, rowData);
    }

    private static String isValidRowData(String[] rowData, String row, String filePath) {
        String errorMessage = null;
        if (rowData.length < 7) {
            errorMessage = "Row should contain at least 7 data.";
            WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
            return errorMessage;
        }

        for (String data : rowData) {
            if (data.trim().isEmpty()) {
                errorMessage = "trade data should not be empty.";
                WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
                return errorMessage;
            }
        }

        return errorMessage;
    }

    private static String validateTradeData(String row, String filePath, String [] rowData) {
        String tradeId = rowData[0].trim();
        String transactionTime = rowData[1].trim();
        String accountNumber = rowData[2].trim();
        String cusip = rowData[3].trim();
        String activity = rowData[4].trim();
        String tradeQuantity = rowData[5].trim();
        String tradePrice = rowData[6].trim();

        String errorMessage = null;

        if (!tradeId.startsWith("TDB")) {
            errorMessage = "trade identifier should start with prefix TDB.";
            WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
            return errorMessage;
        }

        if (cusip.length() > 5 || !cusip.matches("[a-zA-Z]+")) {
            errorMessage = "ticker symbol should be <=5 length and only alphabets.";
            WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
            return errorMessage;
        }

        if (!accountNumber.startsWith("TDB_CUST")) {
            errorMessage = "trade account number should start with prefix TDB_CUST.";
            WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
            return errorMessage;
        }

        if (!activity.matches("SELL|BUY")) {
            errorMessage = "Trade activity should be either buy or sell.";
            WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
            return errorMessage;
        }

//        if (!isValidDateTimeFormat(transactionTime)) {
//        errorMessage = "date should be in 'yyyy-MM-dd HH:mm:ss ' format.";
//            WriteErrorLogs.writeErrorLogsToFile(row + "date should be in 'yyyy-MM-dd HH:mm:ss ' format.", filePath);
//            return errorMessage;
//        }
        errorMessage = validateQuantity(row, filePath, tradeQuantity);
        errorMessage = validatePrice(row, filePath, tradePrice);
        return errorMessage;
    }

    private static String validateQuantity(String row, String filePath, String tradeQuantity) {
        String errorMessage = null;
        if (!tradeQuantity.isEmpty()) {
            try {
                int quantity = Integer.parseInt(tradeQuantity);
                if (quantity <= 0) {
                    errorMessage = "quantity should be > 0.";
                    WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
                    return errorMessage;
                }
            } catch (NumberFormatException e) {
                errorMessage = "quantity is not a valid number.";
                WriteErrorLogs.writeErrorLogsToFile(row + errorMessage, filePath);
                return errorMessage;
            }
        }
        return errorMessage;
    }

    private static String  validatePrice(String row, String filePath, String tradePrice) {
        String errorMessage = null;
        if (!tradePrice.isEmpty()) {
            try {
                double price = Double.parseDouble(tradePrice);
                if (price <= 0) {
                    errorMessage = "price should be > 0.";
                    WriteErrorLogs.writeErrorLogsToFile(row + "price should be > 0.", filePath);
                    return errorMessage;
                }
            } catch (NumberFormatException e) {
                errorMessage = "price is not a valid number.";
                WriteErrorLogs.writeErrorLogsToFile(row + "price is not a valid number.", filePath);
                return errorMessage;
            }
        }
        return errorMessage;
    }
}
