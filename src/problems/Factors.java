package problems;

public class Factors {
    public static void main(String[] args) {
        printFactors(25);
    }

    public static void printFactors (int number) {
        if (number < 1) {
            System.out.println("Invalid Value");
        }
        int reminder = 0;
        for(int i = 1; i <= number; i++) {
            reminder = number % i;
            if (reminder == 0) {
                System.out.println(i);
            }
        }
    }
}