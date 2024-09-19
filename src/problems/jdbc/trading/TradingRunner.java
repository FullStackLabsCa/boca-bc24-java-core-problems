package problems.jdbc.trading;

import problems.jdbc.trading.exception.InvalidThresholdValueException;
import problems.jdbc.trading.service.TradeService;

import java.util.Scanner;

public class TradingRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String caseType;
        double threshold = 0;
        System.out.println("Enter which case you want to run: case1, case2, exit: ");
        caseType = scanner.nextLine();
        if (!caseType.equalsIgnoreCase("exit")) {
            System.out.println("Enter the file path: ");
            String path = "/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src/problems/jdbc/trading/assets/trades_sample_1000.csv";
            if (caseType.equalsIgnoreCase("case1")) {
                System.out.println("Enter threshold value: ");
                do {
                    try {
                        try {
                            threshold = scanner.nextDouble();
                            if (threshold < 1 || threshold > 100)
                                throw new InvalidThresholdValueException("Enter a valid threshold value.");
                            else TradeService.readFileAndInitializeDataSource(path, threshold);

                        } catch (InvalidThresholdValueException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            throw new InvalidThresholdValueException("Enter a valid threshold value.");
                        }
                    } catch (InvalidThresholdValueException e) {
                        System.out.println(e.getMessage());
                        scanner.nextLine();
                    }
                } while (threshold == 0);
            } else if (caseType.equalsIgnoreCase("case2")) {
                TradeService.readFileAndInitializeDataSource(path, 0);
            }
        }
    }
}
