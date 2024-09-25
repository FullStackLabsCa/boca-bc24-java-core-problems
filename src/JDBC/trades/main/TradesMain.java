package JDBC.trades.main;

import JDBC.trades.services.TradesService;

public class TradesMain {

    public static void main(String[] args) {
        TradesService tradesService = new TradesService();
        tradesService.userInput();
    }
}
