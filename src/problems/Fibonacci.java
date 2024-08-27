package problems;

import java.rmi.server.ExportException;
import java.util.*;
import java.util.logging.Logger;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num;
        int number;
        System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        num = sc.nextLine();
        System.out.println(num);
        String regex = "\\d+";
        if(num.matches(regex) == false){
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }else{
             number = Integer.valueOf(num);
            if(number < 4 || number > 47){
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            }
        }


    }

    public static List<Integer> generateFibonacci(int num) {
        int a = 0, b = 1;
        int sum;
        List<Integer> lseries = new ArrayList<>();
        lseries.add(a);
        lseries.add(b);


        for (int i = 2; i < num; i++) {
            sum = a + b;
            lseries.add(sum);
            a = b;
            b = sum;
        }
        return lseries;
    }

}

