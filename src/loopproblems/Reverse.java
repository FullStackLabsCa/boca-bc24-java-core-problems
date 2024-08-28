package loopproblems;

import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        String str = "Gagan";
        String[] arr = str.split("");
        int j = arr.length - 1;
        for (int i = 0; i < str.length() / 2; i++) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            j--;
        }
        System.out.println("Reverse String: " + Arrays.toString(arr));
    }
}
