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
        return x +1;
    }
    // Decrement method using loop
    private static int decrement(int x) {
        return x - 1;
    }

    // Subtract method using loops
    public static int subtract(int a, int b) {
        while (b > 0) {
            a = decrement(a);
            b = decrement(b);
        }
        return a;
    }
}


