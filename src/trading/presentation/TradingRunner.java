package trading.presentation;
import com.zaxxer.hikari.HikariDataSource;
import trading.utility.FileNotExists;
import trading.utility.HitErrorsThresholdException;
import trading.utility.InvalidThresholdValueException;
import trading.database.connection.DatabaseConnectivity;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static trading.service.TradingService.fetchThresholdValue;
import static trading.service.TradingService.readTradingFileAndWriteToQueue;

public class TradingRunner {

    public static double THRESHOLD_VALUE ;// Change to a regular variable
    public static final HikariDataSource DATA_SOURCE;

    static {
        DATA_SOURCE = DatabaseConnectivity.configureHikariCP(); // Ensure this method returns a valid HikariDataSource
        if (DATA_SOURCE == null) {
            throw new IllegalStateException("Failed to initialize DATA_SOURCE");
        }
    }
    public static void main(String[] args) {
         DatabaseConnectivity.configureHikariCP();
        Scanner scanner = new Scanner(System.in);
        File file = null;

        // Loop until a valid file path is provided
        while (true) {
            System.out.println("Enter a file path:");
            String filepath = scanner.nextLine();
            file = new File(filepath);
            if (!file.exists()) {
                System.out.println("File not found. Please try again.");
            } else {
                break; // Valid file found, exit the loop
            }
        }

        System.out.println("Trading File with: Case1, Case2");
        System.out.println("Type '1' for Case 1 or Type '2' for Case 2");

        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            switch (input) {
                case 1:
                    case1(scanner, file);
                    break;
                case 2:
                    case2( file);
                    break;
                default:
                    throw new InputMismatchException("Invalid choice. Please enter 1 or 2.");
            }
        } catch (InvalidThresholdValueException | SQLException | IOException | HitErrorsThresholdException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public static void case1(Scanner scanner, File file) throws SQLException, HitErrorsThresholdException, IOException {
        while (true) {
            try {
                System.out.println("Enter threshold value (1-100): ");
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline
                if (value < 1 || value > 100) {
                    throw new InvalidThresholdValueException("Value must be between 1 and 100.");
                }
                break; // Valid value, exit the loop
            } catch (InvalidThresholdValueException e) {
                System.out.println(e.getMessage() + " Enter again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        try {
            System.out.println("Threshold value set to: " + THRESHOLD_VALUE);
            readTradingFileAndWriteToQueue(new File(file.getPath()));
        } catch (HitErrorsThresholdException | IOException | FileNotExists e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void case2( File file) throws InvalidThresholdValueException, SQLException, HitErrorsThresholdException {
        try {
            fetchThresholdValue(); // Fetch the threshold value after confirming the file path
            System.out.println("Threshold value : " + THRESHOLD_VALUE);
            readTradingFileAndWriteToQueue(new File(file.getPath()));
        } catch (IOException e) {
            throw new FileNotExists("File not found: " + e.getMessage());
        }
    }
}
