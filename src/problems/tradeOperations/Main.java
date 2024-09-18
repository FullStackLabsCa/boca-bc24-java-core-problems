package problems.tradeOperations;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // Step- : Database connection
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.configureHikariCP();

        // Step- :
        TradeValidator tradeValidator = new TradeValidator();

        // Step- : Taking user input
        userInputCMD();


    }

    private static void userInputCMD() {
        TradeValidator tradeValidator = new TradeValidator();
        // 1. The file path will be provided by the user at runtime.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("exit") ){
                break;
            }
            File file = new File(userInput);
//            if (!file.exists()) {
//                System.out.println("File not found. Please provide a valid file path.");
//                continue; // Continue the loop to ask for the file path again
//            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {

//                    System.out.println(line); // check for data before Implementing below code

                    // Split the line by comma
                    String[] fields = line.split(",");

                    // Ensure the row has exactly 5 columns
                    if (fields.length != 5) {
                        System.out.println("Invalid row format: " + line);
                        continue;
                    }

                    // Accessing individual field
                    String trade_id = fields[0];
                    String ticker_symbol = fields[1];
                    String quantity = fields[2];
                    String price = fields[3];
                    String trade_date = fields[4];

                    // validation - trade id
                    if (!tradeValidator.isValidTradeId(trade_id)){
                        System.out.println("Invalid Trade ID: " + trade_id + "in line: " + line);
                        continue;
                    }

                    // validation - ticker symbol
                    if (!tradeValidator.isValidTickerSymbol(ticker_symbol)){
                        System.out.println("Invalid Ticker Symbol: " + ticker_symbol + "in line: " + line);
                        continue;
                    }

                    // validation - quantity
                    if (!tradeValidator.isValidQuantity(String.valueOf(quantity))){
                        System.out.println("Invalid Quantity: " + quantity + "in line: " + line);
                        continue;
                    }

                    // validation - price
                    if (!tradeValidator.isValidPrice(price)){
                        System.out.println("Invalid Price: " + price + "in line: " + line);
                        continue;
                    }

                    // validation - trade date
                    if (!tradeValidator.isValidTradeDate(trade_date)){
                        System.out.println("Invalid Trade Date: " + trade_date + "in line: " + line);
                        continue;
                    }

                    // Printing the fields (or processing them)
                    System.out.println("Valid Trade: ");
                    System.out.println("Trade ID: " + trade_id);
                    System.out.println("Ticker Symbol: " + ticker_symbol);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Price: " + price);
                    System.out.println("Trade Date: " + trade_date);
                    System.out.println("-----------------------------");
                }
                break;
            } catch (FileNotFoundException e) {
//                System.out.println("File Not Found");
//                System.out.   println("java.io.FileNotFoundException: '" + file +"' (No such file or directory)");
                e.printStackTrace();
                System.out.println("Please give path again");
                continue;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
