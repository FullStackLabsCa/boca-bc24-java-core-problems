package upgraded.trade.processor.service;

import com.zaxxer.hikari.HikariDataSource;
import upgraded.trade.processor.model.JournalEntry;
import upgraded.trade.processor.model.Positions;
import upgraded.trade.processor.repository.TradeRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeProcessor implements Runnable {
    private LinkedBlockingQueue<String> tradeQueue;
    private HikariDataSource dataSource;
    int count = 0;

    public TradeProcessor(LinkedBlockingQueue<String> tradeQueue, HikariDataSource dataSource) {
        this.tradeQueue = tradeQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        count++;
        while (true) {
            try {
                processTrade(this.tradeQueue.take());

            } catch (SQLException | InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public void processTrade(String tradeId) throws SQLException {
        TradeRepository tradeRepository = new TradeRepository();
        Connection connection = dataSource.getConnection();
        try {
            String payload = tradeRepository.readRawPayload(tradeId, connection);
            String[] payloadArray = payload.split(",");
            String cusip = payloadArray[3];
            boolean securityValidate = tradeRepository.lookupSecurities(cusip, connection);
            if (securityValidate) {
                connection.setAutoCommit(false);
                JournalEntry journalEntry = journalEntryTransactions(payloadArray, cusip, tradeRepository, connection);
                positionTransaction(journalEntry, tradeRepository, connection);
            }

        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    public JournalEntry journalEntryTransactions(String[] payloadArray, String cusip, TradeRepository tradeRepository, Connection connection) throws SQLException {
        JournalEntry journalEntry = new JournalEntry(
                payloadArray[0],
                payloadArray[2],
                cusip,
                payloadArray[4],
                Integer.parseInt(payloadArray[5]),
                "not_posted",
                LocalDateTime.parse(payloadArray[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        tradeRepository.insertIntoJournalEntry(journalEntry, connection);
        return journalEntry;
    }

    public void positionTransaction(JournalEntry journalEntry, TradeRepository tradeRepository, Connection connection) throws SQLException {
        Positions position;
        position = new Positions(journalEntry.getQuantity(), journalEntry.getAccountNumber(), journalEntry.getSecurityCusp(), 0);
        int[] positionsAndVersion = tradeRepository.lookupPositions(position, connection);
        position.setVersion(positionsAndVersion[1]);
        if (journalEntry.getDirection().equalsIgnoreCase("BUY")) {
            position.setQuantity(positionsAndVersion[0] + journalEntry.getQuantity());
        } else position.setQuantity(positionsAndVersion[0] - journalEntry.getQuantity());
        if (position.getVersion() == 0) {
            tradeRepository.insertIntoPositions(position, connection);
        } else tradeRepository.updatePositions(position, connection);
        tradeRepository.updateJournalEntryStatus(journalEntry.getTradeId(), connection);
    }

}
