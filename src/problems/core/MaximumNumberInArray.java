package problems.core;
@SuppressWarnings("java:S106")
public class MaximumNumberInArray {
    public static void main(String[] args) {
        //Problem 1:Find Maximum in Array
        int[] numberArray = {10,30,20,40,60,50,70,90,80};
        int maxValue = numberArray[0];

        for(int num : numberArray){
            if(num > maxValue) maxValue=num;
        }
        System.out.println("Maximum Value: " + maxValue);
    }
}