package problems.generics;

import java.util.List;

public class ArrayList<T> {
    public static <T> T[] ListToarray( List<T> list,T[]array){

        return list.toArray(array);
    }
    public static void main(String[] args) {
        List< Integer> list=List.of(76,67);
        Integer[]arrayInteger= list.toArray(new Integer[0]);
        System.out.println(list);
        for ( Integer arrayelement: arrayInteger){
            System.out.println(arrayelement);
        }


    }
}
