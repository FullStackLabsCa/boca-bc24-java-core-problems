package problems.tradeoperations;

import problems.tradeoperations.exception_files.InvalidThresholdValueException;
import problems.tradeoperations.exception_files.InvalidThresholdValueRuntimeException;
import problems.tradeoperations.manager.ThresholdManager;

import java.util.Scanner;

public class ThresholdInput {

    private ThresholdInput() {
    }

    public static double threshholdUserFileInput (String[] args) {
        double effectiveThreshold = 0;
        // Handle error threshold
        try {
            ThresholdManager thresholdManager = new ThresholdManager(args);
            Scanner scanner = new Scanner(System.in);
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Please enter a valid error threshold (1-100) or press Enter to use the value from the properties file: ");
                String input = scanner.nextLine();

                if (input.trim().isEmpty()) {
                    // If input is empty, get threshold from properties file
                    effectiveThreshold = thresholdManager.getErrorThreshold();
                    validInput = true;
                } else {
                    validInput = initializeThresholdManager(input, thresholdManager, effectiveThreshold);
//                    try {
//                        double userThreshold = Double.parseDouble(input);
//                        // Create a new ThresholdManager with user input
//                        thresholdManager = new ThresholdManager(userThreshold);
//                        effectiveThreshold = thresholdManager.getErrorThreshold();
//                        validInput = true;
//                    } catch (InvalidThresholdValueException e) {
//                        System.out.println(e.getMessage());
//                    }catch (NumberFormatException e) {
//                        System.out.println("Invalid input. Please enter a number.");
//                    }
                }
            }

            // Display the effective error threshold
            System.out.println("Effective Threshold: " + effectiveThreshold);

        } catch (InvalidThresholdValueRuntimeException e) {
            System.err.println("Critical Error: " + e.getMessage());
            // Terminate the program
            System.exit(1);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        return effectiveThreshold;
    }

    private static boolean initializeThresholdManager(String input, ThresholdManager thresholdManager, Double effectiveThreshold) {
        try {
            double userThreshold = Double.parseDouble(input);
            // Create a new ThresholdManager with user input
            thresholdManager = new ThresholdManager(userThreshold);
            effectiveThreshold = thresholdManager.getErrorThreshold();
            return true; // Indicate valid input
        } catch (InvalidThresholdValueException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return false; // Indicate invalid input
    }

}
