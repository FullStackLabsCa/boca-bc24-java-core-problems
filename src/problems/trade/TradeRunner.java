package problems.trade;

import com.zaxxer.hikari.HikariDataSource;
import problems.trade.Util.TradeFileReader;
import problems.trade.config.HikariCPConfig;
import problems.trade.exceptions.InvalidThresholdRuntimeException;
import problems.trade.model.Trade;
import problems.trade.service.TradeProccesorService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class TradeRunner {

    private static String filePath = "/Users/AnilKumar.Mummadisetti/source/boca-bc24-java-core-problems/resources/TradeSheet.csv";
    public static double tradeThreshold;

    public static List<Trade> validtradeList = new ArrayList<>();
    public static List<String> invalidTradeList = new ArrayList<>();


    public static void main(String[] args) {

        try {
            loadFromConfigProperties();
            loadFilefromUserInput();
            loadThresholdfromUserInput();

            TradeFileReader tradeFileReader = new TradeFileReader();
            TradeProccesorService tradeProccesorService = new TradeProccesorService();

            validtradeList = tradeFileReader.readTradeDataFromCSV(filePath);

            HikariDataSource dataSource = HikariCPConfig.getDataSource();

            int executedTradesCount = tradeProccesorService.tradeDBWriter(dataSource, validtradeList);

            System.out.println(executedTradesCount+" trades executed successfully");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void loadFilefromUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the file path " +
                    "(press Enter to use Default or type 'x' to exit)): ");
            String inputFilePath = scanner.nextLine().trim();
            if (inputFilePath.trim().equalsIgnoreCase("x")) {
                System.out.println("Exiting the program.");
                System.exit(0);
            }
            if (inputFilePath.isEmpty()) {
                break;
            }
            if (inputFilePath.contains(".csv")) {
                filePath = inputFilePath;
                break;
            } else {
                System.out.println("Invalid file path. Please provide a valid .csv file.");
            }
        }
    }

    private static void loadThresholdfromUserInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter the threshold value (press Enter to use from Properties file): ");
                String inputThreshold = scanner.nextLine().trim();

                if (inputThreshold.trim().equalsIgnoreCase("x")) {
                    System.exit(0);
                }
                if (!inputThreshold.isEmpty()) {
                    try {
                        tradeThreshold = Double.parseDouble(inputThreshold);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid threshold value. Please provide a number.");
                        continue;
                    }
                }
                if (tradeThreshold < 0 || tradeThreshold > 100) {
                    System.out.println("Threshold value must be between 0 and 100.");
                    continue;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFromConfigProperties() throws InvalidThresholdRuntimeException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            filePath = properties.getProperty("file.path");
            try {
                tradeThreshold = Double.parseDouble(properties.getProperty("trade.threshold"));
            } catch (NumberFormatException e) {
                throw new InvalidThresholdRuntimeException("Invalid threshold in properties file." +
                        " Fix the application.properties file before re-running the program ");
            }
            if (tradeThreshold < 0 || tradeThreshold > 100) {
                throw new InvalidThresholdRuntimeException("Threshold value must be between 0 and 100.");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
