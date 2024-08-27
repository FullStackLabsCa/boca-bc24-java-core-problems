package genricproblem.ListToArray;
import java.util.List;
public class ListToArray {
    public static <T> T[] listToArray(List<T> list){
        T[] listArray = (T[]) new Object[list.size()];
        for(int i=0; i<listArray.length; i++){
            listArray[i]= list.get(i);
        }
        return listArray;
    }
}
