package problems.codeproblems;

import java.util.Scanner;

public class AlphabetNumberCount {
    public static void main(String[] args) {

        //take string as input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string");
        String stringFromUser = scanner.nextLine();
        System.out.println("Which alphabet or number do you want to count?");
        char userRequirement = scanner.next().charAt(0);

        int countInString = 0;
        for(int i = 0; i < stringFromUser.length(); i++){
            char[] characterArray = stringFromUser.toCharArray();
            if (userRequirement== stringFromUser.charAt(i)) {
                countInString++;
            }

        }
        System.out.println("Here is the count of required alphabet/number: " + countInString);


    }
}
