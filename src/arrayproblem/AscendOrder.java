package arrayproblem;

import java.util.Scanner;

public class AscendOrder {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the values separated by , : ");
        String input= scanner.nextLine();
        String[] stringArray = input.split(",");
        int[] numbers = new int[stringArray.length];
        for(int i=0;i< stringArray.length;i++) {
            numbers[i] = Integer.parseInt(stringArray[i]);
        }
        boolean result;
        result=isSorted(numbers);
        System.out.println("result "+result);
    }
    public static boolean isSorted(int[] array) {
        boolean issorted=true;
        int firstvalue= array[0];
        for (int j : array) {
            System.out.println("value of : " + j);
            if (firstvalue > j) {
                issorted = false;
            }
        }




        return issorted;
    }


}
