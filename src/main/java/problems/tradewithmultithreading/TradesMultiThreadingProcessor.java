//package problems.tradewithmultithreading;
//
//import problems.tradewithmultithreading.chunk.ChunkGenerator;
//import problems.tradewithmultithreading.chunk.ChunkProcessor;
//import problems.tradewithmultithreading.chunk.TradeFileChunkGenerator;
//import problems.tradewithmultithreading.chunk.TradeFileChunkProcessor;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TradesMultiThreadingProcessor {
//
//
//    public static void main(String[] args) throws IOException {
//        String filePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/trades_with_thread.csv";
//        String directoryPath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/filesplitfolder";
//
//
//        TradeFileChunkGenerator services = new TradeFileChunkGenerator();
//        TradeFileChunkGenerator.getChunkSize();
//        TradeFileChunkGenerator.findNumberOfRowsInCSV();
//        TradeFileChunkGenerator.splitTradeCSVFile();
//
//        ChunkGenerator submittingTask = new TradeFileChunkGenerator();
////        submittingTask.generateAndSubmitChunks(filePath);
////        String fileName = "Trade_1.csv";
////        Runnable runnable = new FileTaskRunnable(fileName);
////        runnable.run();
//
////        List<String> chunkedFiles = TradesMultiThreadingServices.splitTradeCSVFile();
////        ChunkProcessor submittingTask = new TradeFileChunkGenerator();
////        submittingTask.submitTaskToThreadPool(directoryPath, chunkedFiles);
//
//
//
//    }
//}
