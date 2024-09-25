package multithread_trade_processing.interfaces;

public record chunksPathAndNumberOfChunks(String filePath, int numberOfFiles) {

    @Override
    public String toString() {
        return "chunksPathAndNumberOfChunks{" +
                "filePath=" + filePath +
                ", numberOfFiles=" + numberOfFiles +
                '}';
    }
}
