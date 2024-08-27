package problems;

public class OddEven {
    public static void main(String[] args) {
        //4- Print first 50 odd numbers
        int number = 100;
        System.out.print("List of odd numbers from 1 to " + number + ": \n");

        for (int i = 1; i <= number; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }

        ;

        //5 Print first 50 even numbers

        System.out.println("\nList of Even numbers from 1 to " + number + ": ");
        for (int j = 1; j <= number; j++) {
            if (j % 2 == 0) {
                System.out.print(j + " ");

            }

        }
    }
}


