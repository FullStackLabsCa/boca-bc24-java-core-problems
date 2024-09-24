package problems.trading_3way_with_object.utility;

import java.util.Scanner;

public class ThresholdValidation {

    public static double thresholdValue(){
        double errorThreshold = -1;
        // Loop to get a valid error threshold
        while (true) {
            try (Scanner scanner = new Scanner(System.in);){
                // Prompt the user to enter the error threshold
                System.out.println("Enter the error threshold (0-100): ");
                errorThreshold = Double.parseDouble(scanner.nextLine());

                // Validate the error threshold
                if (errorThreshold < 0 || errorThreshold > 100) {
                    throw new IllegalArgumentException("Error threshold must be between 0 and 100.");
                }

                // If valid threshold is provided, break the loop
                break;

            } catch (NumberFormatException e) {
                System.err.println("Error: The error threshold must be a valid number. Please try again.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred while entering the threshold: " + e.getMessage());
            }
        }
        return errorThreshold;
    }
}
