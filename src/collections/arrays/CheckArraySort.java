package collections.arrays;

public class CheckArraySort {

    public static boolean isSorted(int[] myArray){
        boolean isSorted = false;

        for (int i = 0; i < myArray.length - 1; i++) {
            if(myArray[i] <= myArray[i+1]){
                isSorted = true;
            } else {
                return false;
            }
        }

        return isSorted;
    }

    public static void main(String[] args) {
        System.out.println(CheckArraySort.isSorted(new int[]{1, 2, 4, 3, 4}));
    }
}
