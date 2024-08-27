package problems;

public class EvenNumberSum {
    public static void main(String[] args) {
//     Add the first 50 even numbers and print the sum

        int number = 100;
        int sum = 0;
        System.out.println("List of Even numbers from 1 to " + number + ": ");
        for (int i = 1; i <= number; i++) {
            System.out.print(i + " ");
            if (i % 2 == 0) {
                sum = i + sum;

            }

        }
        System.out.print("sum : " + sum);


    }


}


