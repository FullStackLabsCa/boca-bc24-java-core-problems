package problems.oldproblems;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter a string: ");
        char[] st = s.nextLine().toCharArray();
        int j = st.length - 1;
        for (int i = 0; i < st.length / 2; i++) {
            char c = st[i];
            st[i] = st[j];
            st[j] = c;
            j--;
        }

        String str = new String(st);
        System.out.println(str);
    }
}
