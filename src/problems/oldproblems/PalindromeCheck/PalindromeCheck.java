package problems.oldproblems.PalindromeCheck;

import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String str = s.nextLine();

//        String str = "Wa@s it a9 c9at I s@aw?";

        char[] st = str.toLowerCase().replaceAll("[^a-z\\d]+", "").toCharArray();
        int j=st.length-1;
        boolean flag = true;
        for(int i=0; i<st.length/2; i++){
            if(st[i] != st[j]){
                flag = false;
                break;
            }
            j--;
        }

        if(flag){
            System.out.println("String is palindrome");
        } else{
            System.out.println("String is not palindrome");
        }
    }
}
