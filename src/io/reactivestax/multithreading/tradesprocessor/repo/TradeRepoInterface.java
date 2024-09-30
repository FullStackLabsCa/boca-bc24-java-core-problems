package io.reactivestax.multithreading.tradesprocessor.repo;

import com.zaxxer.hikari.HikariDataSource;

public interface TradeRepoInterface {
    public void addTradeToTradePayloads(String line, String tradeId, String status, String reason, HikariDataSource dataSource);
}
