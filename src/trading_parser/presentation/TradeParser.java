package trading_parser.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import static trading_parser.service.TradeParserEngine.getThresholdFromCommandLine;
import static trading_parser.service.TradeParserEngine.readTradesFile;
import static trading_parser.utility.TradeParseUtility.configureHikariCP;
import static trading_parser.utility.TradeParseUtility.configureLogger;

public class TradeParser {

    public static void main(String[] args) throws SQLException, IOException {

        getThresholdFromCommandLine();

        String filePath = "trades_sample_1000.csv";
        configureHikariCP();
        Logger logger = configureLogger();
        readTradesFile(filePath, logger);
    }

}