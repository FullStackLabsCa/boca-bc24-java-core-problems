package problems.core;
@SuppressWarnings("java:S106")
public class SumAllNumbersInArray {
    public static void main(String[] args) {
        // Sum all the numbers in array
        int[] numbers = {45,10,50,7,87,12,87,8,9,4,61,23};
        int sum = 0;

        for(int num: numbers){
            sum+=num;
        }

        System.out.println("Sum of the array: "+ sum);
    }

}
