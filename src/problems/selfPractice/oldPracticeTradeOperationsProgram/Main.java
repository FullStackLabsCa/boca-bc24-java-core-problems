//package problems.tradeOperations.oldFiles;
//
//import problems.tradeOperations.manager.DatabaseManager;
//import problems.tradeOperations.UserInputCMD;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//
//        // Step- : Database connection
//        DatabaseManager databaseManager = new DatabaseManager();
//        databaseManager.configureHikariCP();
//
//        // Test connection
//        try (Connection conn = databaseManager.getConnection()) {
//            if (conn != null) {
//                System.out.println("Connected to the database successfully!");
//            }
//        } catch (SQLException e) {
//            System.out.println("Failed to connect to the database: " + e.getMessage());
//        }
//
//
//        /*// Step- :
//        TradeValidator tradeValidator = new TradeValidator();
//*/
//        // Step- : Taking user input
//        UserInputCMD userInputCMD  = new UserInputCMD(databaseManager);
//
//
//    }
///*
//     private static void userInputCMD() {
//        TradeValidator tradeValidator = new TradeValidator();
//        // 1. The file path will be provided by the user at runtime.
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please give me trade file path");
//        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
//        String filePath = scanner.nextLine();
//        File file = new File(filePath);
//        readFile(filePath);
//
//     }
//
//    public static void readFile(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//
//                String[] fields = line.split(",");
//                if (fields.length != 5){
//                    System.out.println("Invalid row (Incorrect numbeer of fields): " + line);
//                    continue;
//                }
//
//                // validation - quantity (integer)
//                try {
//                    int quantity = Integer.parseInt(fields[2]);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid quantity (not an integer): " + fields[2] + " in row: " + line);
//                    continue;
//                }
//
//                // validation - price (decimal)
//                try {
//                    double price = Double.parseDouble(fields[3]);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid price (not a decimal): " + fields[3] + " in row: " + line);
//                    continue;
//                }
//
//                // validation - trade date (yyyy-MM-dd)
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                dateFormat.setLenient(false);
//                try {
//                    dateFormat.parse(fields[4]);
//                } catch (ParseException e) {
//                    System.out.println("Invalid trade date (not in yyyy-MM-dd format): " + fields[4] + " in row: " + line);
//                    continue;
//                }
//
//                System.out.println("Valid row: " + line);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    */
//
//
//   /* private static void userInputCMD() {
//        TradeValidator tradeValidator = new TradeValidator();
//        // 1. The file path will be provided by the user at runtime.
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please give me trade file path");
//        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
//
//        while (true) {
//            String userInput = scanner.nextLine();
//            if (userInput.equals("exit") ){
//                break;
//            }
//            File file = new File(userInput);
////            if (!file.exists()) {
////                System.out.println("File not found. Please provide a valid file path.");
////                continue; // Continue the loop to ask for the file path again
////            }
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//
////                    System.out.println(line); // check for data before Implementing below code
//
//                    // Split the line by comma
//                    String[] fields = line.split(",");
//
//                    // Ensure the row has exactly 5 columns
//                    if (fields.length != 5) {
//                        System.out.println("Invalid row format: " + line);
//                        continue;
//                    }
//
//                    // Accessing individual field
//                    String trade_id = fields[0];
//                    String ticker_symbol = fields[1];
//                    String quantity = fields[2];
//                    String price = fields[3];
//                    String trade_date = fields[4];
//
//                    // validation - trade id
//                    if (!tradeValidator.isValidTradeId(trade_id)){
//                        System.out.println("Invalid Trade ID: " + trade_id + "in line: " + line);
//                        continue;
//                    }
//
//                    // validation - ticker symbol
//                    if (!tradeValidator.isValidTickerSymbol(ticker_symbol)){
//                        System.out.println("Invalid Ticker Symbol: " + ticker_symbol + "in line: " + line);
//                        continue;
//                    }
//
//                    // validation - quantity
//                    if (!tradeValidator.isValidQuantity((quantity))){
//                        System.out.println("Invalid Quantity: " + quantity + "in line: " + line);
//                        continue;
//                    }
//
//                    // validation - price
//                    if (!tradeValidator.isValidPrice(price)){
//                        System.out.println("Invalid Price: " + price + "in line: " + line);
//                        continue;
//                    }
//
//                    // validation - trade date
//                    if (!tradeValidator.isValidTradeDate(trade_date)){
//                        System.out.println("Invalid Trade Date: " + trade_date + "in line: " + line);
//                        continue;
//                    }
//
//                    // Printing the fields (or processing them)
//                    System.out.println("Valid Trade: ");
//                    System.out.println("Trade ID: " + trade_id);
//                    System.out.println("Ticker Symbol: " + ticker_symbol);
//                    System.out.println("Quantity: " + quantity);
//                    System.out.println("Price: " + price);
//                    System.out.println("Trade Date: " + trade_date);
//                    System.out.println("-----------------------------");
//                }
//                break;
//            } catch (FileNotFoundException e) {
////                System.out.println("File Not Found");
////                System.out.   println("java.io.FileNotFoundException: '" + file +"' (No such file or directory)");
//                e.printStackTrace();
//                System.out.println("Please give path again");
//                continue;
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }*/
//}
