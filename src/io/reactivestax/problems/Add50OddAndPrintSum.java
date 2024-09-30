package io.reactivestax.problems;

public class Add50OddAndPrintSum {
    public static void main(String[] args) {
        int evenNumberSum = 0;
        int count = 0;
        int number = 1;

        while (count < 50) {
            evenNumberSum += number;
            number += 2;
            count++;
        }

        System.out.println("The Sum of First 50 Even Numbers is " + evenNumberSum);
    }
}
