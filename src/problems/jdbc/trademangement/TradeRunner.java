package jdbc.trademangement;

import jdbc.trademangement.service.TradeService;

import java.io.File;
import java.util.Scanner;

public class TradeRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        while (true) {
            System.out.println("Please enter valid file path");
            filePath = scanner.nextLine();
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                continue;
            }
           break;
        }
        TradeService.setupDbConnectionAndReadFile(filePath);
    }
}
