package problems.trading;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckUserInputForFile {
    public static String checkFileName(boolean isFileNameValid, String fileName, Scanner scanner) throws FileNotFoundException {
        while (!isFileNameValid) {
            System.out.print("Please enter a file name >>>");
            fileName = scanner.next().trim();
            if (fileName.equals("trade_data")) {
                isFileNameValid = true;
            } else {
                throw new FileNotFoundException("Please enter a valid file name");
            }
        }
        return fileName;
    }
}
