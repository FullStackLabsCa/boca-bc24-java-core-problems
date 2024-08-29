package problems.problems;

//13 Find first duplicate in an array of values

import java.util.ArrayList;

public class FirstDuplicate {

    public static void main(String[] args) {

        ArrayList<Integer> duplicateValues
                = new ArrayList<>();
        int ctr = 0;
        int[] values = {1,2,3,4,5,6,7,2};
        for (int i = 0; i < values.length; i++) {
            ctr++;
            for (int j = ctr; j < values.length; j++) {
                if (values[i] == values[j]) {
                    duplicateValues.add(values[i]);
                    break;
                }
            }
        }

        for (Integer value: duplicateValues) {
            System.out.println("Duplicate: " +  value);
        }

    }
}