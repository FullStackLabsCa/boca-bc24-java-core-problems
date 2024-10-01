package trade.processing.multithreading.dao;

public interface QueProcessor {
    void processAndInsertToJournalDB(String trade);
}
