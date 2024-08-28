package problems.generics.normal;

import java.util.Scanner;

public class CountOccurrences {
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

        System.out.println("The element " + occurence + " occurs " + countOccurrences(arr,occurence) + " times. ");
    }

    public static <T> int countOccurrences(T[] array, T element){
        int count=0;
        for(T item: array) {
            if (element == null) {
                if (item == null) {
                    count++;
                }
            } else {
                if (element.equals(item)) {
                    count++;
                }
            }
        }
        return count;
    }
}