package problems.numbers;

import java.util.ArrayList;
import java.util.Scanner;

public class Factors {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Factors of: ");
        int num = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= num; i+=1) {
            if (num % i == 0){
                arr.add(i);
            }
        }
        System.out.println("Factors of " + num + " are " + arr);
    }
}
