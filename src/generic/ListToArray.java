package generic;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {

    public static <T> T[] listToArray (List<T> list,T[] output) {
        if(list == null || list.isEmpty()){
            output = null;
        } else {
            for (int i = 0; i < list.size(); i++){
            output[i] = list.get(i);
            }
        }
        return output;
    }
}
