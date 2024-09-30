package problems.tradingplatform;

import problems.tradingplatform.customexceptions.HitErrorsThresholdException;
import problems.tradingplatform.services.TradingOperation;
import problems.tradingplatform.customexceptions.InvalidThresholdValueException;

import java.io.File;
import java.util.Scanner;

public class MainRunner {
    public static double threshold = 0.25;

    public static void main(String[] args) throws HitErrorsThresholdException {
        TradingOperation tradingOperation = new TradingOperation();

        Scanner input = new Scanner(System.in);
        File file = null;
        String filePath;

        System.out.println("Choose option from below :- ");
        System.out.println("1. Provide threshold input.");
        System.out.println("2. Go with 25% threshold.");
        System.out.println("3. Exit.");

        int option = input.nextInt();
        input.nextLine();

        switch (option){
            case 1 :
                System.out.println("Enter your own threshold (between 0 and 1, e.g., 0.25 for 25%)  :- ");
                threshold = input.nextDouble();
                input.nextLine();

                while(threshold < 0 || threshold > 1) {


                    System.out.println("Invalid threshold. Please enter a value between 0 and 1.");
                    try {
                        threshold = input.nextDouble();
                        input.nextLine();
                    }catch (RuntimeException e){
                        throw new InvalidThresholdValueException("Invalid threshold type. Required value is between 0 and 1");
                    }
                }
                break;
            case 2 :
                System.out.println("Going forward with the default threshold 25%)  :- ");
                threshold = 0.25;
                break;
            case 3 :
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Exiting...");
                System.exit(0);
        }
        System.out.println("Enter the file path: ");
        filePath = input.nextLine();

        while(true){
            file = new File(filePath);

            if (file.exists()) {
                tradingOperation.tradeReadingOperation(filePath, threshold);
                break;
            }
            else{
                System.out.println("File does not exist. Please type input again");
                throw new HitErrorsThresholdException("No such file or directory");

//                filePath = input.nextLine();
            }
        }
    }
}
