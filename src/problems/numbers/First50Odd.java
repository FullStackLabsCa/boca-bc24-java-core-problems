package problems.numbers;

import java.util.ArrayList;

public class First50Odd {
    public static void main(String args[]) {

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i <= 50; i+=1) {
            if (i%2==0)
            {
                continue;
            }
            else {
                arr.add(i);
            }
        }
        System.out.println(arr);
    }
}
