package problems.old_assignments.problems.problems.add_subtract_numbers;

public class AddSubtractNumbers {
    public static int add(int a, int b) {
        if (b < 0) {
            return subtract(a, -b);
        }
        while (b > 0) {
            a = increment(a);
            b = decrement(b);
        }
        return a;
    }

    // Increment method using loop
    private static int increment(int x) {
        return x + 1;
    }
    // Decrement method using loop
    private static int decrement(int x) {
        return x - 1;
    }

    // Subtract method using loops
    private static int subtract(int a, int b) {
        while (b > 0) {
            a = decrement(a);
            b = decrement(b);
        }
        return a;
    }

    public static void main(String[] args) {
        int result = add(-20, 2);
        int result1 = subtract(10, 20);
        System.out.println("The sum of two numbers: " + result);
        System.out.println("The subtraction of two numbers: " + result1);
    }
}


