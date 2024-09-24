package problems.trading_2way.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator {
    private static final String pattern = "yyyy-MM-DD";
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
