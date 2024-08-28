package problems.old_assignments.problems.problems;

import java.util.Scanner;

public class Factors {
    public static <T extends Number> void findFactors(T number) {
        int num = number.intValue();
        System.out.print("Factors of " + num + " are: ");
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                System.out.print(i + ", ");

            }
        }
    }
}

