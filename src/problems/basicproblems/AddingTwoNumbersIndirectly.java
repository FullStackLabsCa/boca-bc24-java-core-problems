//Add two numbers without directly adding them

import java.util.Scanner;

public class AddingTwoNumbersIndirectly {
    public static void main(String[] args) {


        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter two number to add");
        String[] string = scanner.nextLine().split(" ");
        for (String numb : string) {
            sum += Integer.parseInt(numb);
        }
        System.out.println("sum is " + sum);
    }
}
