package genericsProblems;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListToArrayConversion {

    //Write a generic method listToArray that converts a List<T> to an array of type T[].
    //public static <T> T[] listToArray(List<T> list)

    public static void main (String[] args){
        List<Integer> newList = new ArrayList<>();

        System.out.println("enter elements to list : ");
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            newList.add(input.nextInt());
        }


        Object[] array = listToArray(newList);

        for(Object elem: array){
            System.out.println(elem);
        }

    }

   public static <T>  T[] listToArray(List<T> list){

   T[] array =  (T[])new Object[list.size()];

       for (int i = 0; i < list.size(); i++) {
           array[i] =  list.get(i);

       }

       return array;
   }




}




