package problems.core;

import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("java:S106")
public class GivenInputNumbersListDownAllPossibleFactors {
    public static void main(String[] args) {
        System.out.println("Enter an input numbers");
        Scanner value = new Scanner(System.in);
        int number = value.nextInt();
        ArrayList<Integer> numberList =  new ArrayList<Integer>();

        for (int i = 1; i <= number; i++) {
            if((number%i) ==0)
                numberList.add(i);
        }

        for (int num: numberList){
            System.out.println(num);
        }
    }
}