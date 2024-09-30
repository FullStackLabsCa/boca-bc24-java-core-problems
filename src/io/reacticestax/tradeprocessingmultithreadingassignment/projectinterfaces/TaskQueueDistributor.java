package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

public interface TaskQueueDistributor {
    void submitToTaskDistributionQueue(String tradeId);

    void consultTheQueueDistributorMap(String trade);
}
