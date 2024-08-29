package problems.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DuplicatesInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter the elements of array: ");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int[] mergedArray = findDuplicates(array);

        StringBuilder str = new StringBuilder("[");

        for (int num : mergedArray) {
            str.append(num).append(" ");
        }
        str = new StringBuilder(str.toString().trim().replace(" ", ","));

        System.out.println(str.append("]"));
    }

    public static int[] findDuplicates(int[] array) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j && array[i] == array[j] && !list.contains(array[i])) {
                    list.add(array[i]);
                }
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
