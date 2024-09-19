package problems.tradeOperations;

import problems.tradeOperations.tradeFiles.tradeFileReader;

public class Main {

    public static void main(String[] args) {


        // Thresold Input - either user(CMD) or application,properties

        // Below 2 lines have error for more information refer : note1.md
//        ThresholdInput thresholdInput = new ThresholdInput();
//        double effectiveThreshold = thresholdInput.threshholdUserFileInput();

        double effectiveThreshold = ThresholdInput.threshholdUserFileInput(args);

        // File Path user(CMD) input
        tradeFileReader userInputCMD = new tradeFileReader(effectiveThreshold);

    }

}
