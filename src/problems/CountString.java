package problems;

import java.util.Scanner;

public class CountString {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the value: ");
        String value= scanner.nextLine();
       // value ="sioopiuup";
       // Integer count = 0;
        for(int i=0;i<value.length();i++){
            char ch =value.charAt(i);
            for(int j=i+1;j<value.length();j++){
               if( ch ==value.charAt(j)){
                   System.out.println("repeated value is "+ch);
                   break;
               }
            }

        }
    }
}