package problems.array;

import java.util.ArrayList;

public class FiftyOddNumbers {
    public static void main(String[] args) {

        int count =0;
        ArrayList<Integer> oddNumbers = new ArrayList();

        while(oddNumbers.size()<50){
            count = count+1;
            if(count % 2 != 0){
                oddNumbers.add(count);
            }
        }
        System.out.println(oddNumbers.size()+" "+oddNumbers);
        addOddNumbers(oddNumbers);
    }

    public static void addOddNumbers(ArrayList<Integer> oddNumbers){
        int sum = 0;
        for(Integer val: oddNumbers){
            sum = sum + val;
        }
        System.out.println(sum);
    }
}
