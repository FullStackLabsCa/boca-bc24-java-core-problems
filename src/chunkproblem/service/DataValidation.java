package chunkproblem.ServiceLayer;

public class DataValidation {


    public static boolean isValid(String[] values) {
        if (values.length < 3) {
            return false; // Ensure there are enough values
        }
        try {
            int tradeId = Integer.parseInt(values[0].trim());
            if (tradeId <= 0) {
                return false; // Validate trade_id is a positive integer
            }
        } catch (NumberFormatException e) {
            return false; // trade_id is not a valid integer
        }
        String status = values[1].trim();
        // Validate status (example: it should be either "active" or "inactive")
        if (!status.equals("active") && !status.equals("inactive")) {
            return false;
        }
        String payload = values[2].trim();
        if (payload.isEmpty()) {
            return false;
        }
        return true;
    }
}
