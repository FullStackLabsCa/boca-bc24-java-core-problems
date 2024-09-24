package problems.trading_2way.business_logic;

import problems.trading_2way.utility.DateFormatValidator;
import problems.trading_2way.utility.TradeFieldValidator;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessTradeLine {
    private static final DateFormatValidator dateValidator = new DateFormatValidator();
    private static final TradeFieldValidator fieldValidator = new TradeFieldValidator();

    public static void processTradeLine(String currentLine, PreparedStatement statement) throws IllegalArgumentException, SQLException {
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

        try {
            statement.setString(1, tradeId);
            statement.setString(2, tradeIdentifier);
            statement.setString(3, tickerSymbol);
            statement.setInt(4, quantity);
            statement.setDouble(5, price);
            statement.setDate(6, Date.valueOf(date));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
