package stringsAndArraysProblems;

public class SortedArray {

//    int[] intArray = {1, 7, 3, 2, 8, 4, 9};


    public static boolean isSorted(int[] intArray) {
        int currentElement = intArray[0];
        for (int j : intArray) {
            //Select the first element of the list.
            if (currentElement > j) {
                return false;
                //Compare the selected element with all other elements in the list.
                //For every comparison, if any element is smaller (or larger) than selected element

            } else {
                currentElement = j;
            }

        }
        return true;
    }
}