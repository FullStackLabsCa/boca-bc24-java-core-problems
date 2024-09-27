package problems.tradewithmultithreading;

import java.io.IOException;

public class TradesMultiThreadingProcessor {


    public static void main(String[] args) throws IOException {
        TradesMultiThreadingServices services = new TradesMultiThreadingServices();
        TradesMultiThreadingServices.getChunkSize();
        TradesMultiThreadingServices.findNumberOfRowsInCSV();
        TradesMultiThreadingServices.splitTradeCSVFile();
        String filePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/trades_with_thread.csv";

       // TradesMultiThreadingServices.splitTradeCSVFile(filePath);


    }
}
