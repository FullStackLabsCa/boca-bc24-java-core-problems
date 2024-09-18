package problems.tradeOperations.oldFiles.finalProgram;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserInputCMD {
    private DatabaseConnection databaseManager;

    public UserInputCMD(DatabaseConnection databaseConnection) {
        this.databaseManager = databaseConnection;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        String filePath = scanner.nextLine();
        try {
            TradeFileReader tradeFileReader = new TradeFileReader();
            List<String[]> validTrades = tradeFileReader.readFile(filePath);
            int[] result = TradeDatabaseManager.processBatchInsert(validTrades, databaseConnection);
            // Output the result as before
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        } catch (HitErrorsThresholdException e) {
            throw new RuntimeException(e);
        }
    }
}

