package problems;

public class FindMaximumInArray {
    public static void main(String[] args) {

    }
    public static int findMax(int[] arr) {

        if(arr == null || arr.length==0){
            return -2147483648;
        }
        int max = arr[0];
        for(int i=1; i< arr.length; i++){
            if(arr[i] >= max){
                max = arr[i];
            }
        }
        System.out.println("The max of array is: "+max);
        return max;
    }
}

