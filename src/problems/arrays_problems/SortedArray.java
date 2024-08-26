package problems.arrays_problems;

public class SortedArray {
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("SortedArray.isSorted(new int[]{1, 2, 3, 4}) = "
                + SortedArray.isSorted(new int[]{1, 2, 3, 4}));

        System.out.println("SortedArray.isSorted(new int[]{4, 3, 2, 1}) = "
                + SortedArray.isSorted(new int[]{4, 3, 2, 1}));
    }
}
