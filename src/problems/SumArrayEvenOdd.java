package problems;

public class SumArrayEvenOdd {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 7, 8, 9, 10};
        int sum = 0;
        int sub=0;
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                sum = sum + arr[i];
            } else {
                sub=sub+arr[i];
            }
        }
        System.out.println("print the sum of even number: "+sum);
        System.out.println("print the sum of odd number: "+sub);
    }
}