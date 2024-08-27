package problems;

import java.util.Scanner;

public class SortedArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array:- ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("Enter the elements of the array;- ");
        for(int i=0;i<array.length;i++){
            int element = scanner.nextInt();
            array[i] = element;
        }

        System.out.println("True if sorted else false "+isSorted(array));
    }
    public static boolean isSorted(int[] array){
        for(int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    return false;
                }
            }
        }
        return true;
    }
}
