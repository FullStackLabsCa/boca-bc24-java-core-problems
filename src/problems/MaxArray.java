package problems;

public class MaxArray {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6};
        System.out.println(maxArr(array));
    }
    public static int maxArr(int[] arr) {
        //[2,4,5,1,6]
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
