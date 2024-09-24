package problems.trading_2way.utility;

import java.util.List;

public class PrintSummary {
    public static void printSummary(int totalRows, int successfulInserts, int errorCounter,
                                    List<String> errorReaderMessages, List<String> errorWriterMessages) {
        System.out.println("Summary:");
        System.out.println("Total Records: " + totalRows);
        System.out.println("Successfully Inserted: " + successfulInserts);
        System.out.println("Failed Records: " + errorCounter);
        System.out.println("Reasons for Failures:");
        System.out.println("==== Reader Reason ====");
        errorReaderMessages.forEach(System.out::println);
        System.out.println("==== Writer Reason =====");
        errorWriterMessages.forEach(System.out::println);

    }
}
