package trading_parser.presentation;

import java.io.IOException;
import java.sql.SQLException;

import static trading_parser.service.TradeParserEngine.getThresholdAndFilePathFromCommandLine;
import static trading_parser.service.TradeParserEngine.readTradesFileAndWriteToDatabase;
import static trading_parser.utility.TradeParseUtility.*;

public class TradeParser {

    public static void main(String[] args) throws SQLException, IOException {

        String filePath = getThresholdAndFilePathFromCommandLine();
        configureHikariCP(3306);
        configureLogger("error_log.txt");
        readTradesFileAndWriteToDatabase(filePath);
    }

}