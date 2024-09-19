package problems.selfPractice.oldPracticeTradeOperationsProgram.finalProgram;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TradeValidator {

    public boolean validate(String[] fields, FileWriter errorLogWriter) {
        // Validate number of fields
        if (fields.length != 5) {
            logError("Invalid row (Incorrect number of fields): " + String.join(",", fields), errorLogWriter);
            return false;
        }

        // Validate quantity
        if (!isInteger(fields[2])) {
            logError("Invalid quantity (not an integer): " + fields[2], errorLogWriter);
            return false;
        }

        // Validate price
        if (!isDouble(fields[3])) {
            logError("Invalid price (not a decimal): " + fields[3], errorLogWriter);
            return false;
        }

        // Validate trade date
        if (!isValidDate(fields[4])) {
            logError("Invalid trade date (not in yyyy-MM-dd format): " + fields[4], errorLogWriter);
            return false;
        }

        return true;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDate(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void logError(String message, FileWriter writer) {
        System.out.println(message);
        try {
            writer.write(message + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error logging message: " + e.getMessage());
        }
    }
}

