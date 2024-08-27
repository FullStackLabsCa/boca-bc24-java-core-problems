package StringProblems;

import java.util.Scanner;

public class FindMinMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int[] array= new int[input];
        System.out.println("Enter a value to get minimum and maximum in array");
        System.out.println(findMinMax(array));
    }
    public static int[] findMinMax(int[] array){
        int minValue= array[0];
        int maxValue=array[0];
        for(int i=1 ; i < array.length;i++) {
            if(array[i] > maxValue){
                 maxValue=array[i];
            }
            if(array[i] < minValue){
                minValue = array[i];      ;
            }
        }
        return new int[]{minValue,maxValue};
    }
}
