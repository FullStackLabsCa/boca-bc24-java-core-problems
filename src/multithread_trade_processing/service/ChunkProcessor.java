package multithread_trade_processing.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ChunkProcessor{

    public static ConcurrentHashMap<String, Integer> accToQueueMap = new ConcurrentHashMap<>();

    public static final LinkedBlockingDeque<String> tradesIdQueue1 = new LinkedBlockingDeque<>();
    public static final LinkedBlockingDeque<String> tradesIdQueue2 = new LinkedBlockingDeque<>();
    public static final LinkedBlockingDeque<String> tradesIdQueue3 = new LinkedBlockingDeque<>();

    public void startChunkProcessorPool(String folderPath, int numberOfFiles) {
        List<String> filePaths = getFilesFromFolder(folderPath);

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new ChunkProcessorTask(filePaths));
        }

        executorService.shutdown();
    }

    public List<String> getFilesFromFolder(String folderPath){
        List<String> listOfFiles = new ArrayList<>();

        File directory = new File(folderPath);

        if(directory.exists() && directory.isDirectory()){
            File[] filesList = directory.listFiles();

            if(filesList != null){
                for(File file : filesList){
                    if(file.isFile()){
                        listOfFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }

        return listOfFiles;
    }

}
