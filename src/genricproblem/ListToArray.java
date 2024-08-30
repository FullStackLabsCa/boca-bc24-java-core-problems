package genricproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListToArray {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the value ");
        for(int i=0;i<10;i++){
            list.add(String.valueOf(scanner.nextInt()));
        }

       Object[] obj = listToArray(list, new Integer[list.size()]);
        System.out.println("the  list of value:"+list);
    }

    public static <T> T[] listToArray(List<String> list, Integer[] integers){

        //create the new array of the same type
      T[] listArray = (T[]) new Object[list.size()];

         //did type cast with the help of T[];
      for(int i=0;i< list.size();i++){
          listArray[i]= (T) list.get(i);
         }
         return listArray;
         }

}
