package entity;

import java.util.Arrays;
import java.util.Scanner;

public class DuplicateInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of an array => ");
        int n = scanner.nextInt();
        System.out.println();
        System.out.print("Enter eliments of an array => ");
        int[] array = new int[n];
        for(int i = 0;i<n;i++){
            array[i] = scanner.nextInt();
        }
        System.out.println();
        System.out.println("Duplicates of the array given are => " + Arrays.toString(findDuplicates(array)));
    }

    public static int[] findDuplicates(int[] array){
        int[] newArray = new int[array.length];
        int count = 0;
        for(int i = 0; i<array.length;i++){
            for(int j = i+1;j<array.length;j++){
                if(array[i] == array[j]){
                    newArray[count] = array[i];
                    count++;
                    break;
                }
            }
        }
        int[] result = new int[count];
        System.arraycopy(newArray,0,result,0,count);
        return result;
    }
}
