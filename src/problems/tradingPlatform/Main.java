package problems.tradingPlatform;

import problems.tradingPlatform.databasehelpers.DatabaseOperations;


public class Main {

    public static void main(String[] args)  {
        DatabaseOperations databaseOperations = new DatabaseOperations();
        databaseOperations.readTradeData();
    }
}
