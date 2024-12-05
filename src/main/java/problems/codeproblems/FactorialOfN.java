package problems.codeproblems;

public class FactorialOfN {
    public static void main(String[] args) {
        int n = 12;
        int factorial = 1;
        for(int i = 1; i <= n; i++) {
            factorial*= i;
        }
        System.out.println("Factorial of 12 is: " + factorial);
    }
}
