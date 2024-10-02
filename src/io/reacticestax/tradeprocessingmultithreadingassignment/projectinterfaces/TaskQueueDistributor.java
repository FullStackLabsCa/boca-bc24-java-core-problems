package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

public interface TaskQueueDistributor {
    Integer consultAccountToQueueMap(String account_num);
    void insertToTradeQueue(String account_num, String trade_id) throws InterruptedException;
}
