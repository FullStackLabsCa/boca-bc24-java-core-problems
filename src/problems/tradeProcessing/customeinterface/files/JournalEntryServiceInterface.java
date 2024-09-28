package problems.tradeProcessing.customeinterface.files;

import java.sql.SQLException;

public interface JournalEntryServiceInterface {
    void insertJournalEntry(String[] accountId, String securityId) throws SQLException;
}
