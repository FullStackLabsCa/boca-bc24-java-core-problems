package problems.numbers;

import java.util.ArrayList;

public class AddFirst50Even {
    public static void main(String args[]) {
        int sum = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i <= 100; i+=1) {
            if (i%2==0)
            {
                sum = sum + i;
                arr.add(sum);
            }
            else {
                continue;
            }
        }
        System.out.println("Sum of first 50 even numbers: " + sum);
    }
}
