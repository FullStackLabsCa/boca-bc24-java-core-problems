package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingOdd {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number or type done to end.");
        while (flag) {

            String num = scanner.nextLine();
            if (num.equals("done")) {
                flag = false;
            } else {
                numbers.add(Integer.parseInt(num));
            }
        }
        System.out.println("List of numbers:- "+numbers);
        System.out.println("Count of odd numbers:- "+oddCount(numbers));

    }
    public static int oddCount(List<Integer> list){
        List<Integer> odd = new ArrayList<>();
        int count =0;
        for(int i:list){
            if(i%2!=0){
                odd.add(i);
                count++;
            }
        }
        System.out.println("List of odd numbers "+odd);
        return count;
    }
}
