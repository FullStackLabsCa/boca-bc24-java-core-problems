package problems.arraysandstrings;

import java.util.Scanner;

public class RemoveVowels {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to remove vowels: ");
        String str = scanner.nextLine();
        System.out.println("String with vowels: "+str);
        System.out.println("String without vowels: "+removeVowels(str));

        CountVowels countVowels = new CountVowels();

        countVowels.countVowels(str);
    }

    public static String removeVowels(String input){
        StringBuilder str = new StringBuilder();
        if(!input.isEmpty()){
            for(int i=0; i< input.length(); i++){
                switch (input.toLowerCase().charAt(i)){
                    case 'a', 'e', 'i', 'o', 'u':
                        break;
                    default:
                        str.append(input.charAt(i));
                }
            }
        }

        return str.toString();
    }
}
