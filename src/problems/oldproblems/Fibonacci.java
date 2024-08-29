package problems.oldproblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Please enter a number between 1 and 100: ");
        long n = s.nextInt();

        List<Long> list = new ArrayList<>();

        long first = 0;
        long second = 1;
        list.add(first);
        list.add(second);
        n -= 2;
        while (n != 0) {
            long sum = first + second;
            list.add(sum);
            n--;
            first = second;
            second = sum;
        }

        System.out.println(list);
    }
}
