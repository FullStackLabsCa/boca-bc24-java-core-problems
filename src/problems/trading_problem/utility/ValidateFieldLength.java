package problems.trading_problem.utility;

public class ValidateFieldLength {
    public static void validateFieldsInTradeLine(String[] fields) {
        if (fields.length != 5) {
            throw new IllegalArgumentException("Invalid number of fields in line");
        }

    }
}
