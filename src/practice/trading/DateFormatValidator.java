package practice.trading;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator {
    public static void main(String[] args) {
        String date = "2024-01-09";  // The date to validate
        String pattern = "yyyy-MM-dd";  // Expected date format

        if (isValidDate(date, pattern)) {
            System.out.println("The date is valid.");
        } else {
            System.out.println("Invalid date format.");
        }
    }

    public static boolean isValidDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        sdf.setLenient(false);  // Set lenient to false to enforce strict date parsing
//
        try {
            // Try to parse the date, if it fails, it's an invalid date
            sdf.parse(dateStr);
            return true;  // If no exception is thrown, the date is valid
        } catch (ParseException e) {
            // Catch the ParseException when the date doesn't match the format
            return false;
        }
    }
}
