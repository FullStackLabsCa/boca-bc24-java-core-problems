package basic;

public class Problem7 {
    public static void main(String[] args) {
        int i = 1, sum = 0, count = 0;
        while (count < 50) {
            if (i % 2 != 0) {
                count += 1;
                sum += i;
            }
            i += 1;
        }
        System.out.println("Sum of first 50 odd numbers is: " + sum);
    }
}
