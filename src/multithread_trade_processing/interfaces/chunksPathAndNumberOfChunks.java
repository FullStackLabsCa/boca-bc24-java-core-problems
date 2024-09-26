package multithread_trade_processing.interfaces;

public record chunksPathAndNumberOfChunks(String folderPath, int numberOfFiles) {

    @Override
    public String toString() {
        return "chunksPathAndNumberOfChunks{" +
                "filePath=" + folderPath +
                ", numberOfFiles=" + numberOfFiles +
                '}';
    }
}
