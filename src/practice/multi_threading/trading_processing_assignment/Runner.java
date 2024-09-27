package practice.multi_threading.trading_processing_assignment;

import practice.multi_threading.trading_processing_assignment.service.ChunkGenerator;
import practice.multi_threading.trading_processing_assignment.service.ChunkProcessor;
import practice.multi_threading.trading_processing_assignment.util.UserInputValidator;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        // File path validation and return the file
        final String filePath = UserInputValidator.getFilePath();

        // Validate the number of chunks input and return the int
        final int numberOfChunks = UserInputValidator.getNumberOfChunks();

        // Return the list of chunk file names by providing the main file path and number of chunks required
        final List<String> listOfChunkFileName = ChunkGenerator.fileChunkGenerator(filePath, numberOfChunks);

        // Write the file into the raw trade_payload table after validating the number of fields and how many threads are required and consult and write to Queue
        ChunkProcessor chunkProcessor = new ChunkProcessor();
        chunkProcessor.chunkProcessor(numberOfChunks, listOfChunkFileName);
    }
}
