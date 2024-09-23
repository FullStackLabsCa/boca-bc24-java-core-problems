package tradefileprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainRunner {
    private static final Logger log = LoggerFactory.getLogger(MainRunner.class);

    public static void main(String[] args) throws HitErrorsThresholdException {
        Scanner scanner = new Scanner(System.in);
        double threshold = 0.0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the % of error threshold you want to keep:");
            try {
                threshold = scanner.nextDouble();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("You have entered an invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        try (Connection connection = DatabaseHelper.getConnection()) {

            PropertiesLoader.putProperty("app.database.threshold", threshold);
            TradeFileReader tradeFileReader = new TradeFileReader();
            System.out.println("Connected successfully.");
            List<String[]> listOfTrades = null;
            boolean validFile = false;
            while (!validFile) {
                System.out.println("Please enter the file path: ");
                String filePath = scanner.nextLine();
                try{
                    listOfTrades = tradeFileReader.readFile(filePath);
                    validFile = true;
                } catch (IOException e) {
                    System.out.println("You must have entered the wrong file path, please retry.");
                }
            }
                List<String[]> validTrades = tradeFileReader.filterData(listOfTrades);

                if (TradeFileReader.getInvalidListSize() > TradeFileReader.getThreshold()) {
                    throw new HitErrorsThresholdException("Limit has reached, exiting the program.");
                } else {
                    TradesProcessor tradesProcessor = new TradesProcessor(connection, validTrades);
                    tradesProcessor.processTrades();
                }
            } catch(SQLException e){
                log.error("Database connection error: ", e);
            }  catch(HitErrorsThresholdException e){
                log.error(e.getMessage(), e);
            }
          catch(Exception e){
                throw new HitErrorsThresholdException("Multiple duplication reported, Exiting and adding nothing to database");
            }
        }

}
