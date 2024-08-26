package collections.arrays;

public class MergeArray {

    public static int[] mergeArrays(int[] array1, int[] array2){
        int[] mergedArray = new int[array1.length + array2.length];

        for (int i = 0; i < (array1.length + array2.length); i++) {
            //Method 1
//            if (i < array1.length) {
//                mergedArray[i] = array1[i];
//            } else {
//                mergedArray[i] = array2[i - array1.length];
//            }
            //Method 2
            System.arraycopy(array1,0,mergedArray,0,array1.length);
            System.arraycopy(array2,0,mergedArray,array1.length, array2.length);
        }

        return mergedArray;
    }


    public static void main(String[] args) {
        int[] mergedArray = MergeArray.mergeArrays(new int[]{10, 20}, new int[]{30, 40});

        for(int i : mergedArray){
            System.out.println(i);
        }

    }
}
