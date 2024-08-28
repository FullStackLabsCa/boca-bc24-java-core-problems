package loopproblems;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Number: ");
        int num = scanner.nextInt();
        int sum = 1;
        for(int i = num; i>0; i--){
            sum=sum*i;
            System.out.println(i);
        }
        System.out.println("Sum of Factioral is: "+sum);
    }
}
