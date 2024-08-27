package problems;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = scanner.nextInt();

        System.out.println(number1);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("Inside for Loop");
                break;
            }
            break;
//            System.out.println("Outside of J For Loop");
        }

    }


}
