package problems.trading_2way.utility;

public class TradeFieldValidator {

    public void validateInputTradeLine(String[] fields) {
        if (fields.length == 1) {
            for (String element : fields) {
                if (element == null || element.trim().isEmpty()) {
                    throw new IllegalArgumentException("Trade line absent in the File");
                }
            }
        }
        else if (fields.length != 6) {
            throw new IllegalArgumentException("Invalid Trade Entry");
        }
    }

    public void validateField(String field, String fieldName) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is missing");
        }
    }

    public int validateAndParseQuantity(String quantityField) {
        validateField(quantityField, "Quantity");
        return Integer.parseInt(quantityField);
    }

    public double validateAndParsePrice(String priceField) {
        validateField(priceField, "Price");
        return Double.parseDouble(priceField);
    }

}
