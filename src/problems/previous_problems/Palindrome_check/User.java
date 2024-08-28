package oldproblems.Palindrome_check;

import java.util.Scanner;

public class User {
    public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the string: ");

    String inputString = sc.nextLine();

    Palindrome pl = new Palindrome(inputString);
    }
}

