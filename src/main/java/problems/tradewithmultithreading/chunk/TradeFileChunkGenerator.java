//package problems.tradewithmultithreading.chunk;
//
//import com.zaxxer.hikari.HikariDataSource;
//import problems.tradewithmultithreading.FileTaskRunnable;
//import problems.tradingwiththreads.services.ChunkGenerator;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadPoolExecutor;
//
//
////implementing the interface ChunkGenerator, ChunkProcessorThreadPool
//public class TradeFileChunkGenerator implements ChunkGenerator {
//    static int chunkSize;
//    HikariDataSource dataSource;
//    ExecutorService executorService;
//    String filePath;
//
//
//    //chunk size from application.properties
//    public static int getChunkSize() {
//
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileReader("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/application.properties"));
//            chunkSize = Integer.parseInt(properties.getProperty("numberOfChunks"));
//        } catch (IOException e) {
//            throw new RuntimeException("Some error occurred in application properties" + e);
//        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
//        }
//
//        return chunkSize; //returns the chunkSize = 10;
//    }
//
//    //reading the trades_with_thread file:
//    public static int findNumberOfRowsInCSV() {
//
//        String sourceFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/trades_with_thread.csv";
//
//        int numberOfRowsInTradeCSV = 0;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
//            //skip the header --
//            reader.readLine();
//            while ((reader.readLine() != null)) {
//                numberOfRowsInTradeCSV++;
//            }
//
//        } catch (FileNotFoundException ex) {
//            throw new RuntimeException(ex);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        return numberOfRowsInTradeCSV; //returns rows to be 10,000;
//    }
//
//
//    @Override
//    //From ReadFile interface -->
//
//    public List<String> generateAndSubmitChunks(String filePath) {
//
//        //lines is a list of type String --
//        List<String> lines = new ArrayList<>();
//
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//
//            String line;
//            reader.readLine(); //skips the header
//
//            while ((line = reader.readLine()) != null) {
//                lines.add(line);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //generate a file here -- sir's part
//        String chunkFileNameOne = "Trade_1.csv";
//        String chunkFileNameTwo = "Trade_2.csv";
//        String chunkFileNameThree = "Trade_3.csv";
//        String chunkFileNameFour = "Trade_4.csv";
//        String chunkFileNameFive = "Trade_5.csv";
//        String chunkFileNameSix = "Trade_6.csv";
//        String chunkFileNameSeven = "Trade_7.csv";
//        String chunkFileNameEight = "Trade_8.csv";
//        String chunkFileNameNine = "Trade_9.csv";
//        String chunkFileNameTen = "Trade_10.csv";
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameOne));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameTwo));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameThree));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameFour));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameFive));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameSix));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameSeven));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameEight));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameNine));
//        threadPoolExecutor.submit(new FileTaskRunnable(chunkFileNameTen));
//        return lines;
//
//    }
//
//    //splitting the csv file -->
//    public static void splitTradeCSVFile() throws IOException {
//
//
//        //Source file is used for reading purpose and directory path tells the path where the new files created will be stored
//        String sourceFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/trades_with_thread.csv";
//        String directoryPath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/filesplitfolder";
//
//
//        int linesPerFile = findNumberOfRowsInCSV() / getChunkSize(); // {10000/10 = 1000 lines per file that will be created}
//
//        String line;
//        int currentChunk = 1;
//        int rowNumber = 0;
//
//        //Reading from the source file
//        BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
//        //writing into the destined file that will be in directory
//        BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "/Trade_" + currentChunk + ".csv"));
//
//
//        reader.readLine(); //skipping the header
//
//
//        //while the header is not null, write the line into the file, create a new line, increment the rowNumber from 0 to 1 and so on...
//        while ((line = reader.readLine()) != null) {
//            writer.write(line);
//            writer.newLine();
//            rowNumber++;
//
//            if (rowNumber >= linesPerFile) {
//                writer.close();
////                chunkedFiles.add(directoryPath + "/Trade_" + currentChunk + ".csv");
////                executorService(new TradingChunkProcessor((filepath, datasource));
//                currentChunk++;
//                rowNumber = 0;
//
//
//                //also, the chunkSize allowed is till 10, so chunk <= 10, till its true -> write to the files, else break;
//                if (currentChunk <= getChunkSize()) {
//                    writer = new BufferedWriter(new FileWriter(directoryPath + "/Trade_" + currentChunk + ".csv"));
//                } else {
//                    break;
//                }
//            }
//        }
//
//        writer.close();
//
//    }
//
//
//
////    @Override
////    public  void submitTask(String directoryPath, List<String> chunkedFiles) throws IOException {
////
//////       chunkedFiles =  splitTradeCSVFile();
////        //Executor interface provides a single execute() method to submit Runnable instances for execution
////        ExecutorService executorService = Executors.newFixedThreadPool(10);
////
////        //Submit the task to the executor -
////        for(String csvFile : chunkedFiles) {
//////            executorService.submit();
////        }
////        executorService.shutdown();
////    }
//    //        executor.execute(new TradingChunkProcessor(chunkedFiles, dataSource));
//}
//
