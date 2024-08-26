package problems.oldproblems.FactorsNum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactorsNum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Please enter a number to find factors for it: ");
        int num = s.nextInt();
        int n=num;
        List<Integer> list = new ArrayList<>();

        while(n>=1){
            if(num % n == 0){
                list.add(n);
            }
            n--;
        }

        System.out.println("Factors are: " + list);
    }
}
