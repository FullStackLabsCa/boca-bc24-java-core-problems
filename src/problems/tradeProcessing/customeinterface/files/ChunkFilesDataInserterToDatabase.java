package problems.tradeProcessing.customeinterface.files;

public interface ChunkFilesDataInserterToDatabase {
    void insertIntoDatabase(String validatedData, String status) throws Exception;
}
