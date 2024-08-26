package problems.generics.countoccurrences;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        CountOccurrences countOcc = new CountOccurrences();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter how many numbers you want to enter: ");
        int n = s.nextInt();
        Integer[] array = new Integer[n];

        for (int i=0; i<n; i++){
            array[i] = s.nextInt();
        }

        System.out.println("Enter an element to look in the list: ");
        Integer element = s.nextInt();
        System.out.println("Count: "+countOcc.countOccurrences(array, element));

    }
}
