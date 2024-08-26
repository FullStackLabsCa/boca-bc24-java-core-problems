package problems.oldproblems.FirstDuplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstDuplicate {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the count of number you want to input: ");
        int size = s.nextInt();
        System.out.println("Please enter the numbers: ");
        int[] arr = new int[size];

        for(int i=0; i<size; i++){
            arr[i] = s.nextInt();
        }
        int[] uniqueArr = new int[arr.length];

        int duplicate = 0;

        List<Integer> li = new ArrayList<>();

        for (int j : arr) {
            if (!li.contains(j)) {
                li.add(j);
            } else {
                duplicate = j;
                break;
            }
        }

        if(duplicate !=0){
            System.out.println("First duplicate number is: " + duplicate);
        } else {
            System.out.println("No duplicate found");
        }
    }
}
