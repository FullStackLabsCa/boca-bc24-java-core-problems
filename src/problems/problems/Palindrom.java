package problems.problems;

import java.util.Scanner;

public class Palindrom {
    public static void main(String[] args) {


        Scanner Obj = new Scanner(System.in);
        String line = Obj.nextLine();
        line = line.replaceAll(" ", "");
        line = line.replaceAll("[^a-zA-Z0-9]", "");

        int i = 0;
        boolean flag = true;

        while (i < line.length() / 2) {
            if (line.charAt(i) != line.charAt(line.length() - i - 1)) {
                flag = false;
                break;
            }
            i++;

        }
        if (flag) {
            System.out.println("Palindrom");
        } else {
            System.out.println("Not Palindrom");
        }
    }
}