package problems;

public class FabonacciSeries {
    public static void main(String[] args) {
        int firstValue = 0;
        int secondValue = 1;
        int thirdValue=0;
        int[] arr = new int[10];
        for (int i = 0; i<arr.length; i++) {
            thirdValue = firstValue + secondValue;
            secondValue = firstValue;
            firstValue = thirdValue;
            if(thirdValue<=100){
            arr[i] = thirdValue;


        }}
        for (int num : arr) {
            System.out.println("the value of "+num);

        }
    }
}