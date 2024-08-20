package problems;

import java.util.Scanner;

public class ShiftElementsInArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size of an array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements of array: ");

        for(int i=0; i<size; i++){
            arr[i] = scanner.nextInt();
        }

        System.out.println("Enter number of positions to switch: ");
        int position = scanner.nextInt();

        shiftArray(arr, position);

        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void shiftArray(int[] array, int positions){
        while(positions-- !=0){
            int temp = array[0];
            for(int i=0; i<array.length; i++){
                if(i == array.length-1){
                    array[0] = temp;
                } else{
                    int tempLocal = array[i+1];
                    array[i+1] = temp;
                    temp = tempLocal;
                }
            }
        }

    }
}
