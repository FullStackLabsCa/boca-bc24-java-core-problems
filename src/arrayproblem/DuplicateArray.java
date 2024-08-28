package arrayproblem;

import java.util.Scanner;

public class DuplicateArray {
    public static void findDuplicates(int[] array){
        boolean findDuplicate=false;
        for(int i=0;i< array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]==array[j]){
                    System.out.println("Duplicate found :" + array[i]);
                    findDuplicate=true;
                    break;     //exiting the inner loop to avoid printing same duplicate again and again
                }
            }
        }
if(!findDuplicate){
    System.out.println("No Duplicate Found");
}
    }

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the number of elements in the array : ");
        int size= scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter " + size + " elements to find duplicate in the array");
        for(int i=0;i<size;i++){
            array[i]= scanner.nextInt();
        }
        //find and print duplicates
        findDuplicates(array);
        scanner.close();
    }
}
