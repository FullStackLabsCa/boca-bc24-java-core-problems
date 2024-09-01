package genricproblem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ListToArray {
    public static <T> T[] listToArray(List<T> list, Object[] objects){
        if(list==null || list.isEmpty() ) {
            return null;
        }
        T[] listArray = (T[]) new Object[list.size()];
        for(int i=0; i<listArray.length; i++){
            objects[i]= list.get(i);
        }
        return (T[]) objects;
    }

    public static void main(String[] args) {
        List<Integer> listofIntegers = new ArrayList<>();
        listofIntegers.add(34);
        listofIntegers.add(654);
        listofIntegers.add(43);
        Object[] lists = ListToArray.listToArray(listofIntegers, new Integer[0]);
        for(Object array : lists) {
            System.out.print(array+" ");
        }
        List<String> strList = Arrays.asList("code1", "code2", "code3");
        Object[] strLists = ListToArray.listToArray(strList, new String[0]);
        for(Object stray : strLists){
            System.out.print(stray+" ");
        }


    }

}