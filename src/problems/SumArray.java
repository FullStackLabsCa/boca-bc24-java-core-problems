package problems;

public class SumArray {
    public static void main(String[] args) {
        int[] arr={2,3,5,7,8,9,1,3,5};
        int sum=0;
        for (int j : arr) {
            sum = sum + j;
        }
        System.out.println(sum);
    }
}