package arrayproblems;

public class MergeArray {
    public static void main(String[] args) {
    int[] array1 = {1,2};
    int[] array2 = {3,4};
   int[] merged = mergeArrays(array1,array2);
        System.out.println("Merged array: "+merged);
    }
    public static int[] mergeArrays(int[] array1, int[] array2){
        int[] mergedArray = new int[array1.length + array2.length];
        for (int i=0;i<array1.length;i++){
            mergedArray[i] = array1[i];
        }
        for (int i=0; i<array2.length;i++){
            mergedArray[array1.length] = array2[i];
        }

        return mergedArray;
    }

}
