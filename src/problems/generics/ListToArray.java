package problems.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListToArray {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter 10 numbers: ");
        for(int i=0; i<10; i++){
            li.add(s.nextInt());
        }
        Object[] arr = listToArray(li);

        for(Object elem: arr){
            System.out.println(elem);
        }
    }

    public static <T> T[] listToArray(List<T> list){
        T[] array = (T[]) new Object[list.size()];

        for(int i=0; i<list.size(); i++){
            array[i] = list.get(i);
        }

        return array;
    }
}
