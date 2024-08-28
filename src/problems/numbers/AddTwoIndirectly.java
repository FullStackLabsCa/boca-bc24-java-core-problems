package problems.numbers;

public class AddTwoIndirectly {
    public static void main(String args[]) {
        int a = 5;
        int b = 6;
        int c;

        c = add(a, b);  // Indirect addition
        System.out.println("The sum of " + a + " and " + b + " is: " + c);
    }

    public static int add(int num1, int num2) {
        return num1 + num2;  // Perform addition and return the result
    }
}
