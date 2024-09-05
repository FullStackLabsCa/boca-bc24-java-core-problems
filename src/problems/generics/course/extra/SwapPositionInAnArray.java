//Write a generic method to exchange the positions of two different elements in an array.

package problems.generics.course.extra;

import java.util.Arrays;
import java.util.Scanner;

public class SwapPositionInAnArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What size of an array do you need? ");
        int size= scanner.nextInt();

        String[] array= new String[size];
        scanner.nextLine();

        System.out.println("Enter elements one by one: ");
        for(int i=0; i<size; i++){
            array[i]= scanner.nextLine();
        }

        System.out.println("Enter first element in an array you would like to match: ");
        String firstElement= scanner.nextLine();
        System.out.println("Enter second element in an array you would like to match: ");
        String secondElement= scanner.nextLine();

        System.out.println(Arrays.toString(swapElement(array, firstElement, secondElement)));

        scanner.close();
    }

    public static  <T> T[] swapElement(T[] arr, T element1, T element2){
        int indexelement1 = 0;
        int indexelement2 = 0;
        int tempIndex = 0;
        T tempValue = null;

        for(int i=0;i<arr.length;i++){
            if (arr[i].equals(element1)){
                indexelement1 = i;
                tempIndex = indexelement1;
                tempValue= arr[tempIndex];
            } else if (arr[i].equals(element2)) {
                indexelement2 = i;
            }
        }
        arr[indexelement1] = arr[indexelement2];
        arr[indexelement2] = tempValue;
        return arr;
    }
}
