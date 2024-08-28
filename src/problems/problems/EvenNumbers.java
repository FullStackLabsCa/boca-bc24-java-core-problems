package problems.problems;

public class EvenNumbers {
    public static void main(String[] args) {
        int n = 50;
        int count = 0;
        int number = 0;

        while (count < n) {
            if (number % 2 == 0) {
                System.out.println(number);
                count++;
            }
            number++;
        }
    }
}
