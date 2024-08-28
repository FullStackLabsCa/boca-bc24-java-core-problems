package problems.problems;

public class EvenNmuberSum {
    public static void main(String[] args) {
        int n = 50;
        int count = 0;
        int number = 1;
        int sum = 0;

        while (count < n) {
            if (number % 2 == 0) {
                sum += number;
                count++;
            }
            number++;
        }

        System.out.println("Sum of the first " + n + " even numbers: " + sum);
    }
}
