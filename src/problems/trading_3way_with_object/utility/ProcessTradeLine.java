package problems.trading_3way_with_object.utility;

import problems.trading_2way.utility.DateFormatValidator;
import problems.trading_2way.utility.TradeFieldValidator;
import problems.trading_3way_with_object.Trade;

public class ProcessTradeLine {
    static final DateFormatValidator dateValidator = new DateFormatValidator();
    private static final TradeFieldValidator fieldValidator = new TradeFieldValidator();

    public static problems.trading_3way_with_object.Trade processTradeLine(String currentLine) throws IllegalArgumentException {
        String[] fields = currentLine.split(",");

        fieldValidator.validateInputTradeLine(fields);

        String tradeId = fields[0];
        String tradeIdentifier = fields[1];
        String tickerSymbol = fields[2];
        int quantity = fieldValidator.validateAndParseQuantity(fields[3]);
        double price = fieldValidator.validateAndParsePrice(fields[4]);
        String date = fields[5];

        fieldValidator.validateField(tradeId, "trade_id");
        fieldValidator.validateField(tradeIdentifier, "trade_identifier");
        fieldValidator.validateField(tickerSymbol, "ticker_symbol");
        fieldValidator.validateField(date, "trade_date");

        if (!dateValidator.isValidDate(date)) {
            throw new IllegalArgumentException("Date format is invalid");
        }

        return new Trade(tradeId, tradeIdentifier, tickerSymbol, quantity, price, date);
    }
}
