package problems.string;

import java.util.Scanner;

public class RepeatString {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println(" Enter a Value ");
        String name = sc.nextLine();
        String[]arr;
        int length= name.length();
         int i;
        //name.charAt(i);
        for ( i = 0; i<= length; i++)
            System.out.println(name.charAt(i));


    }
}
