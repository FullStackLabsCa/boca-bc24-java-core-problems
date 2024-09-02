package stringproblems;

import java.util.Scanner;

public class FindMinMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value to get minimum and maximum in array");
        String  input = scanner.nextLine();
        String[] inputs = input.split(",");
        int[] intInputArray = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            intInputArray[i] = Integer.parseInt(inputs[i].trim());
        }
        int[] result = findMinMax(intInputArray);
        System.out.println("Maximum number"+intInputArray[0]);
        System.out.println("Maximum number"+intInputArray[1]);
    }
    public static int[] findMinMax(int[] array){
        int minValue=  array[0];
        int maxValue= array[0];
        for(int i=0 ; i < array.length;i++) {
            if(array[i] > maxValue){
                 maxValue=array[i];
            }
            if(array[i] < minValue){
                minValue = array[i];
            }
        }
        return new int[]{minValue,maxValue};
    }
}
