package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Abhay");
        names.add("Nimavat");

        String[] namesArray = listToArray(names, new String[names.size()]);
        for (String name : namesArray) {
            System.out.println(name);
        }
    }
    public static <T> T[] listToArray(List<T> list,T[] array){
     for(int i=0;i<list.size();i++){
         array[i] = list.get(i);
     }

        return array;
    }
}
