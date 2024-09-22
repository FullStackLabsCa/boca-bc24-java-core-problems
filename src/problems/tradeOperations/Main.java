package problems.tradeOperations;

import problems.tradeOperations.exceptionFiles.HitErrorsThresholdException;
import problems.tradeOperations.tradeFiles.TradeRWFile;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws HitErrorsThresholdException {


        // Thresold Input - either user(CMD) or application,properties

        // Below 2 lines have error for more information refer : note1.md
//        ThresholdInput thresholdInput = new ThresholdInput();
//        double effectiveThreshold = thresholdInput.threshholdUserFileInput();

        double effectiveThreshold = ThresholdInput.threshholdUserFileInput(args);

        // File Path user(CMD) input
        TradeRWFile rwFile = new TradeRWFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        System.out.println("/Users/Jay.Shah/Downloads/trades_sample_1000.csv");
        System.out.println("src/problems/tradeOperations/sourcesFiles/trades_sample.csv");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        rwFile.readFile(filePath, effectiveThreshold);


    }

}
