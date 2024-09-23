package tradingExample;
import tradingExample.exceptionTrading.InvalidThresholdValueException;
import tradingExample.modelTrading.TradeTransaction;
import tradingExample.serviceTrading.TradeService;
import java.util.Scanner;


public class TradeRunner {
    public static void main(String[] args) throws InvalidThresholdValueException {
        Scanner scanner = new Scanner(System.in);
        String caseNo;
        double threshold = 0;

        System.out.println("Enter the case you want to run: case1 and case2");
        caseNo = scanner.nextLine();

        if (!caseNo.equalsIgnoreCase("exit")) {
            String path = "/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/tradingExample/utilityTrading/trades_sample_1000.csv";

            if (caseNo.equalsIgnoreCase("case1")) {
                System.out.println("Enter threshold value: ");
                while (true) {
                    if (scanner.hasNextDouble()) {
                        threshold = scanner.nextDouble();
                        if (threshold < 1 || threshold > 100) {
                            System.out.println("Enter a valid threshold value (1-100):");
                        } else {
                            TradeService tradeService = new TradeService();
                            tradeService.readFileAndInitializeDataSource(path, threshold);
                            break; // Valid input, exit the loop
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric value:");
                        scanner.next(); // Clear the invalid input
                    }
                }
            } else if (caseNo.equalsIgnoreCase("case2")) {
                TradeService tradeService = new TradeService();
                tradeService.readFileAndInitializeDataSource(path, threshold);
            } else {
                System.out.println("Invalid case number. Please enter 'case1' or 'case2' or 'exit' to quit.");
            }
        }
        scanner.close(); // Close the scanner to avoid resource leak
    }
}

