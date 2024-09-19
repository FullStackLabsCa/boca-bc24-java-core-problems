package trading_parser.utility;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import trading_parser.presentation.TradeParser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static trading_parser.service.TradeParserEngine.failedInsertsCount;
import static trading_parser.service.TradeParserEngine.successfullInsertsCount;

public class TradeParseUtility {

    public static HikariDataSource dataSource;

    public static Logger logger;
    //Utility
    public static Logger configureLogger() throws IOException {
        FileHandler fileHandler = new FileHandler("error_log.txt");
        fileHandler.setFormatter(new CustomFormatter()); // Use a simple text format
        logger = Logger.getLogger(TradeParser.class.getName());
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
        return logger;
    }

    //Utility
    public static void configureHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
        config.setUsername("root");
        config.setPassword("password123");

        // Optional HikariCP settings
        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
    }

    //Utility
    public static void logFileValidityForErrors(int success, int tried, int errorCount, double threshold) {
        threshold = (tried)*(threshold/100);
        System.out.println("Trades Processed = " + success);
        System.out.println("Number of Traded Failed to process = " + errorCount);
        System.out.println("Successful Inserts = " + successfullInsertsCount);
        System.out.println("Failed Inserts = " + failedInsertsCount);
        double parsingErrorPercentage = ((double) errorCount / (tried))*100;
        System.out.println("Parsing Error Percentage = " + parsingErrorPercentage + "%");
        double insertionErrorPercentage = ((double) failedInsertsCount / tried)*100;
        System.out.println("Insertion Error Percentage = " + insertionErrorPercentage + "%");

        if(errorCount > threshold || failedInsertsCount > threshold){
            throw new HitErrorsThresholdException();
        }
    }

    //Utility
    public static Date LocalDateToDate (LocalDate localDate) {
        // Step 1: Convert LocalDate to LocalDateTime (start of the day)
        LocalDateTime localDateTime = localDate.atStartOfDay();

        // Step 2: Convert LocalDateTime to ZonedDateTime (specifying a time zone)
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        // Step 3: Convert ZonedDateTime to Date
        Date date = Date.from(zonedDateTime.toInstant());

        System.out.println("LocalDate: " + localDate);
        System.out.println("Converted Date: " + date);

        return date;
    }

}

//Utility
class CustomFormatter extends SimpleFormatter {
    @Override
    public String format(java.util.logging.LogRecord record) {
        return record.getMessage() + "\n"; // Log only the message
    }
}
