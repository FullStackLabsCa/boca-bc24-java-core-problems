package problems;

import java.util.List;
//PROBLEM : GENERIC METHOD TO CONVERT LIST TO ARRAY
public class ListToArray {

    public static <T> T[] listToArray(List<T> list, T[] array){
        if(list == null){
            return null;
        }
        Object[] tempArray = new Object[list.size()];
        for(int i = 0; i< list.size();i++){
            tempArray[i] = list.get(i);
        }
        return (T[])tempArray;

    }
}
