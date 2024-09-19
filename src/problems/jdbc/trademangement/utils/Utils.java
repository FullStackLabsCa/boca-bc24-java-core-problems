package jdbc.trademangement.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static boolean isValidDateFormat(String dateString) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        if (!dateString.matches(regex)) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateInput(String rowLine) {
        String [] rowData = rowLine.split(",");
        if (rowData.length < 5) {
            writeErrorLogsToFile(rowLine + " - " + "Row should contain atleast 5 data.");
            return false;
        }
        String tradeId = rowData[0], tickerSymbol = rowData[1], tradeDate = rowData[4];
        int quantity = Integer.parseInt(rowData[2]);
        double price = Double.parseDouble(rowData[3]);

        if (!tradeId.startsWith("T0", 0)) {
            writeErrorLogsToFile(rowLine + " - " + "trade id should start with prefix T0.");
            return false;
        }

        if (tickerSymbol.length() > 5 || !tickerSymbol.matches("[a-zA-Z]+")) {
            System.out.println("!tickerSymbol.matches(\"[a-zA-Z]\")==" + !tickerSymbol.matches("[a-zA-Z]+"));
            writeErrorLogsToFile(rowLine + " - " + "ticker symbol should be <=5 length and only alphabets.");
            return false;
        }

        if (!isValidDateFormat(tradeDate)) {
            writeErrorLogsToFile(rowLine + " - " + "date should in 'yyyy-MM-dd' format.");
            return false;
        }

        if (quantity <= 0) {
            writeErrorLogsToFile(rowLine + " - " + "quantity should be > 0.");
            return false;
        }

        if (price <= 0) {
            writeErrorLogsToFile(rowLine + " - " + "price should be > 0.");
            return false;
        }

        return true;
    }

    public static void writeErrorLogsToFile(String errorMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trademangement/logs/error_log.txt", true))){
            writer.write(errorMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("ERROR>> Write error logs.");
            e.printStackTrace();
        }
    }
}
