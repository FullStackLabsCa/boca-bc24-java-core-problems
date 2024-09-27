package practice.multi_threading.trading_processing_assignment;

import practice.multi_threading.trading_processing_assignment.service.ChunkGenerator;
import practice.multi_threading.trading_processing_assignment.service.WriteRawFileToPayloadDatabase;
import practice.multi_threading.trading_processing_assignment.util.UserInputValidator;

import java.util.List;

public class Runner {
    public static void main(String[] args) {

        //file path validate and return the file
        final String filePath = UserInputValidator.getFilePath();

        //validate the numberofchunks input and return the int
        final int numberOfChunks = UserInputValidator.getNumberOfChunks();

        //return the list file chunks by providing the main file path and number of chunks required
        final List<String> listOfChunkFileName = ChunkGenerator.fileChunkGenerator(filePath, numberOfChunks);

        //writing the file into the raw trade_payload table after validating the number of fields and how many threads are required
        WriteRawFileToPayloadDatabase writeRawFileToPayloadDatabase = new WriteRawFileToPayloadDatabase();
        writeRawFileToPayloadDatabase.writeRawFileToDatabase(numberOfChunks, listOfChunkFileName);

    }

}
