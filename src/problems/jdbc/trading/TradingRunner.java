package problems.jdbc.trading;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.database.DatabaseConnection;
import problems.jdbc.trading.exception.InvalidThresholdValueException;
import problems.jdbc.trading.service.TradeService;

import java.util.Scanner;

public class TradingRunner {
    public static void main(String[] args) {
        HikariDataSource dataSource = DatabaseConnection.configureHikariCP("3306", "transactions", "password123");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter which case you want to run: case1, case2, exit: ");
        String caseType = scanner.nextLine();
        System.out.println("Enter the file path: ");
        String path = scanner.nextLine();
        TradeService tradeService = new TradeService();
        if (caseType.equalsIgnoreCase("case1")) {
            double threshold = 0;
            while (threshold == 0) {
                System.out.println("Enter threshold value: ");
                String thresholdString = scanner.nextLine();
                try {
                    threshold = tradeService.validateThreshold(thresholdString);
                } catch (InvalidThresholdValueException e) {
                    System.out.println(e.getMessage());
                }
            }
            tradeService.readFileAndInitializeDataSource(path, threshold, dataSource);
        } else if (caseType.equalsIgnoreCase("case2")) {
            tradeService.readFileAndInitializeDataSource(path, 0, dataSource);
        }
    }


}
