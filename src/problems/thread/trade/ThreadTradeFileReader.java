package problems.thread.trade;

import java.io.FileNotFoundException;

public class ThreadTradeFileReader {
    public void fileReader(String fileName) throws FileNotFoundException {
        ThreadTradeService.filePath = "";
        // Check if the read file exists and delete it at the start of execution
//        ThreadTradeService.checkReadLogFileExistOrNot();

        // Check if the write file exists and delete it at the start of execution
//        ThreadTradeService.checkWriteLogFileExistOrNot();
        //checking file name from the user input
        ThreadTradeService.isFileExist = false;
        while (!ThreadTradeService.isFileExist) {
            if (fileName.equals("trades")) {
                ThreadTradeService.filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/src/problems/thread/trade/rawdata/" + fileName + ".csv";
                ThreadTradeService.isFileExist = true;
                System.out.println("File found in the system");
            } else {
                throw new FileNotFoundException("Please enter a valid file name");
            }
        }
    }
}
