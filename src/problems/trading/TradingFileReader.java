//package problems.trading;
//
//import java.io.*;
//
//public class TradingFileReader {
//
//    public Tradingfile(String trade_id, String ticker_symbol, Integer quantity, Double price, String trade_date) {
//        this.
//        }
//
//    }
//
//    // Step 1: Read transactions from a comma-delimited file
//    public static void readTradingFileAndWriteToFile(String filePath) {
//    int counter = 0;
//try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//    String line;
//    while ((line = reader.readLine() != null)){
//        counter++;
//        String[] data = line.split("\\,");
//
//    }
//}
//    }
//}
//
////    public static void main(String[] args) {
////        //Step 1 - using scanner and FileReader
////        // a.file url provided by user:
////        Scanner scanner = new Scanner(System.in);
////        //getting the path from user:
////        System.out.println("Enter the csv file path");
////        String filePathFromUser = scanner.nextLine();
////
////        // b.creating file object for specified path from user
////        File tradingFile = new File(filePathFromUser);
////
////        // c. writing column names in the csv file
////        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tradingFile))) {
////            //adding the column headers
////            //use comma as the delimiter
////            scanner.useDelimiter(",");
//            writer.write("trade_id, ticker_symbol, quantity, price, trade_date");
//            writer.newLine();
//        } catch (IOException e) {
//            System.err.println("Error writing in csv file");
//            e.printStackTrace();
//            scanner.close();
//            //return;
//
//            try (Scanner fileScanner = new Scanner(new FileReader(tradingFile))) {
//                fileScanner.useDelimiter(",");
//
//                while (fileScanner.hasNextLine()) {
//                    String line = fileScanner.nextLine();
//                    Scanner scanForLine = new Scanner(line);
//                    scanForLine.useDelimiter(",");
//
//
//                    String trade_id = scanForLine.next();
//                    String ticker_symbol = scanForLine.next();
//                    Integer quantity = scanForLine.nextInt();
//                    Double price = scanForLine.nextDouble();
//                    String trade_date = scanForLine.next(); //in the format yyyy-MM-dd
//
//                    scanForLine.close();
//
//                }
//            } catch (IOException f) {
//                System.err.println("Error occured in reading csv file");
//                f.printStackTrace();
//            }
//
//        }
//        //close the thing with finally
//    }
//}
//
