package problems.trading_3way_with_object.utility;

import java.util.List;

public class PrintSummary {
    public static void printSummary(int totalRows, int successfullyParsedCounter ,int successfulInserts, int errorReaderCounter,int errorBusinessLogicCounter,
                                     List<String> errorReaderMessages, List<String> errorWriterMessages) {
        System.out.println("Summary:");
        System.out.println("Total Records: " + totalRows);
        System.out.println("Failed Records for Reader/Parse logic: " + errorReaderCounter);
        System.out.println("Successful Records for Reader/ParseLogic: " + successfullyParsedCounter);
        System.out.println("Successfully Inserted in the DB: " + successfulInserts);
        System.out.println("Failed Records for Business Logic: " + errorBusinessLogicCounter);
        System.out.println("Reasons for Failures:");
        System.out.println("==== Reader Reason ====");
        errorReaderMessages.forEach(System.out::println);
        System.out.println("==== Writer Reason =====");
        errorWriterMessages.forEach(System.out::println);
    }

}
