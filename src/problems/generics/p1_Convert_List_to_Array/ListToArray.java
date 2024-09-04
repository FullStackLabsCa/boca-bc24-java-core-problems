package problems.generics.p1_Convert_List_to_Array;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ListToArray {
    public static <T> T[] listToArray(List<T> list,T[] array) {

        if (list == null || list.isEmpty()){
            return null;

        }
        else {

            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);

            }
            return array;
        }

    }


}
