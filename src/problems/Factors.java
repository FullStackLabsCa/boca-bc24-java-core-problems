package problems;

import java.util.ArrayList;
import java.util.List;

public class Factors {
    public static void main(String[] args) {
        printFactors(6);
    }

    public static void printFactors(int a){
        List <Integer> factors = new ArrayList<>();
        for(int i =1;i<=a;i++){
            if(a%i==0){
                factors.add(i);
            }
        }

        System.out.println(factors);
    }
}
