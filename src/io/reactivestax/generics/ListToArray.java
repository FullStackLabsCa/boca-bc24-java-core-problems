package io.reactivestax.generics;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {

    public static void main(String[] args) {
        List<String> listString = new ArrayList<>();
        List<Integer> listInteger = new ArrayList<>();
        listString.add("Ankit");
        listString.add("Joshi");
        listInteger.add(1);
        listInteger.add(101);
    }

    public static <T> T[] listToArray(List<T> list, T[] array){
       if(list == null || list.isEmpty()){
           return null;
       }

        for (int i=0; i< list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
}
