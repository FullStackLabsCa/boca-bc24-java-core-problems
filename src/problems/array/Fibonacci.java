package problems.array;

import java.util.ArrayList;

public class Fibonacci {

    public static void main(String[] args) {

        int input =8;

        int n1 = 0;
        int n2 = 1;
        int temp=0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(n1);
        arrayList.add(n2);
        for (int i=0; i<input-2; i++){
            temp = n1+n2;
            n1=n2;
            n2=temp;
            arrayList.add(temp);
        }
        System.out.println(arrayList);
    }
}
