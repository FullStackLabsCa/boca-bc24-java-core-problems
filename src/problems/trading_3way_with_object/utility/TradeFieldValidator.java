package problems.trading_3way_with_object.utility;

public class TradeFieldValidator {
    private static final DateFormatValidator dateValidator = new DateFormatValidator();

    public static void validateAndParseTradeLine(String[] fields) {
        validateInputTradeLine(fields);
        String tradeId = fields[0];
        String tradeIdentifier = fields[1];
        String tickerSymbol = fields[2];
        int quantity = validateAndParseQuantity(fields[3]);
        double price = validateAndParsePrice(fields[4]);
        String date = fields[5];
        validateField(tradeId, "trade_id");
        validateField(tradeIdentifier, "trade_identifier");
        validateField(tickerSymbol, "ticker_symbol");
        validateField(date, "trade_date");
        if (!dateValidator.isValidDate(date)) {
            throw new IllegalArgumentException("Date format is invalid");
        }
    }

    private static void validateInputTradeLine(String[] fields) {
        if (fields.length == 1) {
            for (String element : fields) {
                if (element == null || element.trim().isEmpty()) {
                    throw new IllegalArgumentException("Trade line absent in the File");
                }
            }
        }
//        else if (fields.length != 6) {
//            throw new IllegalArgumentException("Invalid Trade Entry, Supposed to have 6 fields");
//        }
    }
    private static void validateField(String field, String fieldName) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is missing");
        }
    }

    private static int validateAndParseQuantity(String quantityField) {
        validateField(quantityField, "Quantity");
        return Integer.parseInt(quantityField);
    }

    private static double validateAndParsePrice(String priceField) {
        validateField(priceField, "Price");
        return Double.parseDouble(priceField);
    }
}
