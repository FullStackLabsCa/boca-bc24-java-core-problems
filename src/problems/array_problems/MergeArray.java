package problems.array_problems;

public class MergeArray {
    public static void mergeArrays(int[]arr1, int[]arr2){
        int[] mergedArray = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, mergedArray, 0,arr1.length);
        System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);
        for (int element : mergedArray) {
            System.out.print(element+", ");
        }
        System.out.println();


    }
    public static void mergeArrayNew(int[]arr1, int[]arr2){
        int[] copy = new int[arr1.length + arr2.length];
        System.arraycopy(arr1,0,copy,0,arr1.length);
        System.arraycopy(arr2,0,copy,arr1.length, arr2.length);
        for (int element : copy) {
            System.out.print(element+", ");
        }
    }
    public static void main(String[] args) {

        mergeArrays(new int[]{1, 2}, new int[]{3, 4});
        mergeArrayNew(new int[]{10, 20}, new int[]{30});
    }
}
