package TradeProcessingMultiThreadingAssignment.Service.newpackage;

public interface TaskQueueDistributor {
    void submitToTaskDistributionQueue(String tradeId);

    void consultTheQueueDistributorMap(String trade);
}
