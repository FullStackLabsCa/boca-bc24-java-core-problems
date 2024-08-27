package previous;

import java.util.Scanner;

public class RepeatElement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter String you want to check =>");
        String input = scanner.nextLine();
        System.out.println("Enter alphabet you want to count =>");
        String element = scanner.nextLine();
        int count = 0;

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == element.charAt(0)){
                count++;
            }
        }
        System.out.println("" + count);
    }

}
