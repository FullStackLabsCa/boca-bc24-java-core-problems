package problems.trading_1way_without_object.utility;

public class ValidateFieldLength {
    public static void validateFieldsInTradeLine(String[] fields) {
        if (fields.length != 5) {
            throw new IllegalArgumentException("Invalid number of fields in line");
        }

    }
}
