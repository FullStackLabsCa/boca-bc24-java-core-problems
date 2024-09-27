package problems.tradewithmultithreading;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


//implementing the interface ReadFile
public class TradesMultiThreadingServices implements ChunkGenerator {
    static int chunkSize;


    //chunk size from application.properties
    public static int getChunkSize() {

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/application.properties"));
            chunkSize = Integer.parseInt(properties.getProperty("numberOfChunks"));
        } catch (IOException e) {
            throw new RuntimeException("Some error occurred in application properties" + e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return chunkSize; //returns the chunkSize = 10;
    }

    //reading the trades_with_thread file:
    public static int findNumberOfRowsInCSV() {

        String sourceFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/trades_with_thread.csv";

        int numberOfRowsInTradeCSV = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
            //skip the header --
            reader.readLine();
            while ((reader.readLine() != null)) {
                numberOfRowsInTradeCSV++;
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return numberOfRowsInTradeCSV; //returns rows to be 10,000;
    }


    @Override
    //From ReadFile interface -->

    public List<String> readsFileAndGenerateChunks(String filePath) {

        //lines is a list of type String --
        List <String> lines = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            reader.readLine(); //skips the header

            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    //splitting the csv file --
    public static void splitTradeCSVFile() throws IOException {

        //Source file is used for reading purpose and directory path tells the path where the new files created will be stored
        String sourceFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/utilities/trades_with_thread.csv";
        String directoryPath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradewithmultithreading/filesplitfolder";


        int linesPerFile = findNumberOfRowsInCSV() / getChunkSize(); // {10000/10 = 1000 lines per file that will be created}

        String line;

        //Reading from the source file
        BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
        int currentChunk = 1;
        int rowNumber = 0;
        reader.readLine(); //skipping the header

        //writing into the destined file that will be in directory
        BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "/Trade_" + currentChunk + ".csv"));

        //while the header is not null, write the line into the file, create a new line, increment the rowNumber from 0 to 1 and so on...
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
            rowNumber++;

            //while you write, check if the rowNumber is >= lines/file (1>=1000 FALSE...... 1000 = 1000 TRUE)
            // -> when it gets true, close writing into file
            // update the chunk from 1 to next
            // again put the rowNumber to 0 for the starting of a new chunk

            if (rowNumber >= linesPerFile) {
                writer.close();
                currentChunk++;
                rowNumber = 0;


                //also, the chunkSize allowed is till 10, so chunk <= 10, till its true -> write to the files, else break;
                if (currentChunk <= getChunkSize()) {
                writer = new BufferedWriter(new FileWriter(directoryPath + "/Trade_" + currentChunk + ".csv"));
                writer.write(line);
                writer.newLine();
            } else {
                break;
                }
            }

        }
        // when all the things in while loop are done, close the writer
        writer.close();

    }

}

