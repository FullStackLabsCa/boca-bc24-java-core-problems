package problems.numbers;

import java.util.ArrayList;

public class AddFirst50Odd {
    public static void main(String args[]) {
        int sum = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i <= 100; i+=1) {
            if (i%2==0)
            {
                continue;
            }
            else {
                sum = sum + i;
                arr.add(sum);
            }
        }
        System.out.println("Sum of first 50 odd numbers: " + sum);
    }
}
