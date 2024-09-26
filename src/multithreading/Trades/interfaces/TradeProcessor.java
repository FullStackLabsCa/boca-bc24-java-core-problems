package multithreading.Trades.interfaces;

public interface TradeProcessor {
    void processTrade(String tradeId);

    void loadTrade(String tradeId);

    void lookupSecurity(String cusip);

    void insertJournalEntry();

    void upsertPosition();
}
