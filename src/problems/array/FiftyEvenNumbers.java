package problems.array;

import java.util.ArrayList;

public class FiftyEvenNumbers {
    public static void main(String[] args) {

        int count =0;
        ArrayList<Integer> evenNumbers = new ArrayList();
        while(evenNumbers.size()<50){
            count = count+1;
            if(count % 2 == 0){
                evenNumbers.add(count);
            }
        }
        System.out.println(evenNumbers.size()+" "+evenNumbers);
        addEvenNumbers(evenNumbers);

    }
    public static void addEvenNumbers(ArrayList<Integer> evenNumbers){
        int sum = 0;
        for(Integer val: evenNumbers){
            sum = sum + val;
        }
//        n =50;
//        int sum = 2 * n * (n + 1) / 2;
        System.out.println(sum);
    }
}
