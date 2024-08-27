package problems.old_assignments.first_assigment_25th_June_2024.maxInArray;

import java.util.Arrays;

public class UsingJavaStream {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 7, 2, 9, 5};

        System.out.println(Arrays.stream(numbers).max()
                                    .orElseThrow(() -> new IllegalArgumentException("Array is Empty")));

    }
}
