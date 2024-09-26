package mathematics.problem;

public class SumOfFiftyNumber {
    public static void main(String[] args) {
        int sum = 0;
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                count++;
                sum = sum + i;
            }
        }
        System.out.println("the sum of first fifty numbers are : " + sum);
    }
}
