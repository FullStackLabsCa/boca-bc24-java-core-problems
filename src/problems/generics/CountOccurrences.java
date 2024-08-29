package problems.generics;

import java.util.Scanner;

public class CountOccurrences {

    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;

        for(T elem: array){
            if(elem != null && elem.equals(element)) count++;
            else if(elem == null && element == null) count ++;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter how many numbers you want to enter: ");
        int n = s.nextInt();
        Integer[] array = new Integer[n];

        for (int i=0; i<n; i++){
            array[i] = s.nextInt();
        }

        System.out.println("Enter an element to look in the list: ");
        Integer element = s.nextInt();
        System.out.println("Count: "+ countOccurrences(array, element));

    }
}

