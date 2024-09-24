package problems.trading_3way_with_object.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator {

    private static final String pattern = "yyyy-MM-dd"; // Fixed format
    private final SimpleDateFormat simpleDateFormat;

    public DateFormatValidator() {

        this.simpleDateFormat = new SimpleDateFormat(pattern);
    }

    public boolean isValidDate(String dateStr) {
        try {
            simpleDateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
