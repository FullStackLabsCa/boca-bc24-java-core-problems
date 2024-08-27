package problems.old_assignments.problems.problems;

public class FirstDuplicate {
    //13 Find first duplicate in an array of numbers
    public static void main(String[] args) {
        int[] intArray = new int[]{5, 6, 6, 5};

        int i;
        first:
        for (i = 0; i < intArray.length; i++) {
            second:
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[i] == intArray[j]) {
                    System.out.println(" result is: " + intArray[i]);
                    break first;
                }
            }
        }

    }
}
