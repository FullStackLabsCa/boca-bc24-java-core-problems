package problems;

import java.util.ArrayList;

public class ListFactors {

    public ArrayList<Integer> listFactors(int number){

        ArrayList<Integer> listOfFactors = new ArrayList<>();

        for(int i = 1; i <= number; i++){
            if(number%i == 0) {
                listOfFactors.add(i);
            }
        }

        return listOfFactors;
    }

    public static void main(String[] args) {
        ListFactors listOfFactors = new ListFactors();

        System.out.println(listOfFactors.listFactors(10));
    }
}
