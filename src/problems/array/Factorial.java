package problems.array;
//Calculate the factorial of number n (1 >>n <= 12)
/*

for the factorial problem …

Here’s a very simple explanation of how to calculate the factorial of a number:

Start with the number you want to find the factorial of. Let's say it's 5.
Multiply that number by every whole number less than it, one at a time, until you get to 1.
So for 5, you would do:

        5 times 4 is 20.
        20 times 3 is 60.
        60 times 2 is 120.
        120 times 1 is 120.
So, the factorial of 5 is 120.

In short, to find the factorial of a number:

Start with the number.
Keep multiplying it by each smaller number down to 1.
The final result is the factorial.
The factorial of 5 (written as 5!) is 120.
*/

import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {

        String input = readInput();
        int factorial = Integer.parseInt(input);
        if(factorial <12 && factorial >1) {
            System.out.println(findFactorial(factorial));
        }else {
            System.out.println("Invalid!!");
            readInput();
        }

    }
    public static String  readInput(){
        System.out.println("Enter between: 1 and 12 ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int findFactorial(int factorial){
        int fact = 1;
            for (int i = 1; i <= factorial; i++) {
                fact = fact*i;
            }
        return fact;

    }
}
