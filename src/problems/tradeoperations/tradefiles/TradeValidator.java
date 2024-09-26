package problems.tradeoperations.tradefiles;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TradeValidator {

    private final FileWriter errorLogWriter;
    private String cons = " in row: ";

    public TradeValidator(FileWriter errorLogWriter) {
        this.errorLogWriter = errorLogWriter;
    }

    public boolean isValidRow(String[] fields, String line) {
        if (fields.length != 6) {
            logError("Invalid row (Incorrect number of fields): " + line);
            return false;
        }

        return isInteger(fields[3], line, "quantity")
                && isDouble(fields[4], line, "price")
                && isValidDate(fields[5], line);

    }

    private boolean isInteger(String value, String line, String fieldName) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logError("Invalid " + fieldName + " (not an integer): " + value + cons + line);
            return false;
        }
        return true;
    }

    private boolean isDouble(String value, String line, String fieldName) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            logError("Invalid " + fieldName + " (not a decimal): " + value + cons + line);
            return false;
        }
        return true;
    }

    private boolean isValidDate(String date, String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            logError("Invalid trade date (not in yyyy-MM-dd format): " + date + cons + line);
            return false;
        }
        return true;
    }

    private void logError(String errorMessage) {
        System.out.println(errorMessage);
        try {
            errorLogWriter.write(errorMessage + System.lineSeparator());
            errorLogWriter.flush(); // Ensure immediate write
        } catch (IOException e) {
            System.out.println("Failed to log error: " + e.getMessage());
        }
    }
}
