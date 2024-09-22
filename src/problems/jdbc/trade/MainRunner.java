package problems.jdbc.trade;

import problems.jdbc.trade.validator.FilePathValidator;

import java.util.Scanner;

public class MainRunner {
    static String filePath;

    public static void main(String[] args) throws Exception {
        TradeFileReaderImpl tradeFileReader = new TradeFileReaderImpl();
        boolean isValidFilePath = false;
        while (!isValidFilePath) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Provide the file path in following format");
            System.out.println("/Users/Suraj.Adhikari/sources/student-mode-programs/boca-bc24-java-core-problems/src/problems/jdbc/trade/trade_data.csv");
            filePath = scanner.nextLine();
            isValidFilePath = FilePathValidator.isValidPath(filePath.trim());
            if (!isValidFilePath) {
                System.out.println("Not a valid file path");
            }
        }
        tradeFileReader.processFile(filePath.trim());
    }
}
