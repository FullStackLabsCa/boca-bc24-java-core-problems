package trade_analytic_multithreading;

public class TradeAnalyticLauncher {
    public static void main(String[] args) {
        DatabaseConnector.configureHikariCP();
        CliDashboard.positionsDBQuery();
    }
}
