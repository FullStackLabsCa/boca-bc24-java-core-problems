package trading_parser.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import static trading_parser.service.TradeParserEngine.*;
import static trading_parser.utility.TradeParseUtility.*;

public class TradeParser {

    public static void main(String[] args) throws SQLException, IOException {

        getThresholdFromCommandLine();

        String filePath = "trades_sample_1000.csv";
        configureHikariCP();
        Logger logger = configureLogger();
        readTradesFileAndWriteToDatabase(filePath, logger);
    }

}