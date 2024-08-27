package previous;

import java.util.Arrays;
import java.util.OptionalInt;

public class MaxInArray {
    public static void main(String[] args) {
        int[] number = new int[]{1, 23, 33, 223, 233, 23};
        OptionalInt maxNum;
        maxNum = Arrays.stream(number).max();
        System.out.println(maxNum);

    }
}
