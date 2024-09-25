package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.TradeReading;
import multithread_trade_processing.interfaces.chunksPathAndNumberOfChunks;

import java.io.*;
import java.util.Scanner;

public class TradeReader implements TradeReading {

    @Override
    public chunksPathAndNumberOfChunks readFileAndCreateChunks(String filePath, String fileType) {
          try(Scanner fileReader = new Scanner(new FileReader(filePath))){

              int numOfFilesGenerated = 0;
              int maxNumOfLines = getChunkSizeFromPropertiesFile();
              boolean newFileCreationNeeded = true;
              String fileNameUnderProcessing = null;
              int linesRead = 0;
              String folderPath = "/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/src/multithread_trade_processing/data/";

              while(fileReader.hasNextLine()) {

                  if(newFileCreationNeeded){
                    fileNameUnderProcessing = folderPath+"trade_chunk_" + numOfFilesGenerated + ".csv";
                    newFileCreationNeeded = false;
                  }
                  try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameUnderProcessing, true))) {
                      if ((linesRead >= maxNumOfLines) || !fileReader.hasNextLine()) {
                          writer.close();
                          numOfFilesGenerated++;
                          newFileCreationNeeded = true;
                          linesRead = 0;
                      } else {
                          String data = fileReader.nextLine();
                          linesRead++;
                          writer.append(data);
                          writer.newLine();
                      }
                  }
              }

              return new chunksPathAndNumberOfChunks(folderPath, numOfFilesGenerated);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
    }

    public int getChunkSizeFromPropertiesFile(){

        return 2000;
    }

    public static void main(String[] args) {
        TradeReader reader = new TradeReader();

        chunksPathAndNumberOfChunks result =  reader.readFileAndCreateChunks("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/src/multithread_trade_processing/trades.csv", null);
        System.out.println("result = " + result);
    }
}
