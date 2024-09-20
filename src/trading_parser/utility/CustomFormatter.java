package trading_parser.utility;

import java.util.logging.SimpleFormatter;

//Utility
public class CustomFormatter extends SimpleFormatter {
    @Override
    public String format(java.util.logging.LogRecord record) {
        return record.getMessage() + "\n"; // Log only the message
    }
}
