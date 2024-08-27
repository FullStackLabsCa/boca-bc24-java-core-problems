package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactorsOfANumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> resultArray = new ArrayList<>();
        System.out.println("Enter a number: ");
        int number = Integer.valueOf(scanner.nextLine());
        int divisor = 1, quotient = number;
        while (divisor < quotient) {
            if(number%divisor == 0){

                resultArray.add(divisor);
                quotient = number/divisor;
                resultArray.add(quotient);
                // System.out.println("divisor : " + divisor + "Quotient : " + quotient );
                divisor++;
            }
            else{
                divisor++;
            }
        }
        resultArray.stream()
                .sorted()
                .forEach(n-> System.out.print ( n + " "));
    }
}