package problems.generics.normal;

import java.util.Scanner;

public class CountOccurence {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the size of an array: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        String[] arr= new String[size];

        System.out.println("Enter element to insert in an array: ");
        for (int i = 0; i < size; i++) {
            String element = scanner.nextLine();
            arr[i] = element;
        }

        System.out.println("Enter the element to count the occurrence of it: ");
        String occurence= scanner.nextLine();

        System.out.println("The element " + occurence + " occurs " + countOccurence(arr,occurence) + " times. ");
    }

    public static <T> int countOccurence(T[] array, T element){
        int count=0;
        T elementToMatch = (T) element;
        for(int i=0; i< array.length; i++){
            if(array[i].equals(elementToMatch)){
                count++;
            }
        }
        return count;
    }
}