package problems;

import java.util.Arrays;
import java.util.Scanner;

public class AdvancedCalculator {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        boolean exit = true;
        double answer = 0;
        while (exit) {
            System.out.println("Enter the operation you want to perform");
            String input = obj.nextLine().trim().replaceAll("\\s","");
            String[] ch = new String[input.length()];
            if (input.equals("x")) {
                exit = false;
                break;
            }
            else {
                for(int i = 0;i<input.length(); i+=2){
                    double left = Character.getNumericValue(i);
                    answer=left;
                    String symbol = ch[i+1];
                    double right = Character.getNumericValue(i+2);


                    ch[i] = String.valueOf(input.toCharArray()[i]);
                }


            }
            System.out.println(Arrays.toString(ch));

        }
    }

}
