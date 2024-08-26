package problems.array;

import java.util.ArrayList;

public class FindFirstDuplicate {

//    13 Find first duplicate in an array of numbers

    public static void main(String[] args) {
        int list[] = new int[]{6, 5, 6, 3, 7, 8, 3, 1, 5};
        ArrayList<Integer> temp = new ArrayList();

        for (int i = 0; i < list.length; i++) {

            if (temp.contains(list[i])) {
                System.out.println(list[i]);
                break;
            } else {
                temp.add(list[i]);
            }
        }

    }
}
