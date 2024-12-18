package io.reactivestax.problems.stringproblems;

public class SortArray {

    int[] arrayOne = {2, 3, 6, 1};

    public boolean isSorted() {

        boolean isArraySorted = false;

        for (int i = 0; i < arrayOne.length - 1; i++) {
            if (arrayOne[i] < arrayOne[i + 1]) {
                isArraySorted = true;
            } else
                isArraySorted = false;
        }
        return isArraySorted;
    }

    public static void main(String[] args) {
        SortArray sortArray = new SortArray();
        System.out.println("Is array sorted = " + sortArray.isSorted());
    }
}
