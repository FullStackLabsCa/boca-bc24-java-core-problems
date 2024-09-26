package problems.tradeProcessing.customeinterface.files;

public interface QueueDistributorConcurrentMapInterface {
    void assignQueue(String accountNumber); // Assigns queue to account_number if not already assigned
    int getQueue(String accountNumber); // Retrieves the assigned queue number for the account_number
}
