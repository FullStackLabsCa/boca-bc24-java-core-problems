package problems;

import java.util.ArrayList;
import java.util.List;

public class SubractionUtilityClass {
    public static List<Integer> convertIntToArray(int num) {
        List<Integer> arrayInt = new ArrayList<>();
        while (num > 0) {
            arrayInt.add(num % 10);
            num = num / 10;
        }
        return reverseArray(arrayInt);
    }

    public static List<Integer> reverseArray(List<Integer> list) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public static List<Integer> arraySizeEquaotor(List<Integer> list1, List<Integer> list2) {
        int list1Size = list1.size();
        int list2Size = list2.size();
        for (int i = 0; i < (list1Size - list2Size); i++) {
            int temp = list2.get(list2.size() - 1);
            list2.add(temp);
            for (int j = list2.size() - 2; j > 0; j--) {
                list2.set(j, list2.get(j - 1));
            }
            list2.set(0, 0);
        }
        return list2;
    }
}
