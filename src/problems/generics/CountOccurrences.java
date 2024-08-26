package problems.generics;

import java.util.Scanner;

public class CountOccurrences {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter how many numbers you want to enter: ");
        int n = s.nextInt();
        Integer[] array = new Integer[n];

        for (int i=0; i<n; i++){
            array[i] = s.nextInt();
        }

        System.out.println("Count: "+countOccurrences(array, 2));

    }

    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;

        for(T elem: array){
            if(elem.equals(element)) count++;
        }

        return count;
    }
}
