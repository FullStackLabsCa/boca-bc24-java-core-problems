package problems.numbers;

import java.util.ArrayList;

public class First50Even {
    public static void main(String args[]) {

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i <= 50; i+=1) {
            if (i%2==0)
            {
                arr.add(i);
            }
            else {
                continue;
            }
        }
        System.out.println(arr);
    }
}
