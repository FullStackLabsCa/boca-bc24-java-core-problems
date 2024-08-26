package practice.generic.generic_methods_examples.problem_3_print_elements;

import java.util.Arrays;

public class PrintElements {
    public static <T> void printArray(T[] array) {
        for (T elements : array) {
            System.out.print(elements + " ");
        }
        System.out.println();
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }
}
