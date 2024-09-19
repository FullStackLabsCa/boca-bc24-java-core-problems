package problems.trading;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.repository.TradingRepository;
import problems.trading.services.TradingService;
import problems.trading.tradingmodel.TradingValues;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static problems.trading.databaseconnection.TradingDatabaseConnection.configureHikariCP;
import static problems.trading.repository.TradingRepository.prepareStatements;
import static problems.trading.services.TradingService.readTradingFileAndWriteToFile;
import static problems.trading.services.TradingService.setupDBConnectionAndRunFileReading;

public class TradingProcessor {
    public  static  HikariDataSource dataSource;
    public static List<TradingValues> batch = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path");
        String filePath = scanner.nextLine();
       // File tradingFile = new File(filePath);
        dataSource = TradingDatabaseConnection.configureHikariCP();
        Connection connection = TradingService.connectToDatabase();
        setupDBConnectionAndRunFileReading(connection, filePath);




    }
}
