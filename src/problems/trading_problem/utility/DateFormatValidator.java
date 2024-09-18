package problems.trading_problem.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator {
    static String pattern = "yyyy-MM-DD";
    public static boolean isValidDate(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;

        }
    }
}
