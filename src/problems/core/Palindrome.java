package problems.core;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value");
        String name = sc.nextLine();
        boolean flag = true;

        String[] arr = name.split("");
        int j = arr.length - 1;
        for (int i = 0; i <= arr.length / 2; i++){
            if(!arr[i].equalsIgnoreCase(arr[j])){
                flag = false;
            }
            j--;
        }
            if(flag){
                System.out.println("Palindrome");
            } else System.out.println("Not palinfdrome");

        }

}
