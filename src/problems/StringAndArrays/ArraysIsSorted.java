package problems.StringAndArrays;

public class ArraysIsSorted {
    public static void main(String[] args) {

        int[] firstArray = {1, 2, 3,2, 2, 4, 3,1,4};
//        int[] firstArray = {10, -3, 7, 2};
        System.out.println("Is sorted array : "+isSorted(firstArray));

    }

    public static boolean isSorted(int[] firstArray) {

        int tempVal = 0;
        boolean isSorted =false;
        for (int num : firstArray) {
            if (tempVal < num) {
                tempVal = num;
                isSorted = true;
            } else {
                isSorted = false;
            }
        }
        return isSorted;
    }
}
