package stringproblem;

import java.util.Scanner;

import static java.lang.System.out;

public class RemoveVowel {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        out.println("Enter the value : ");
        String input = scanner.nextLine();
        String  vowels = removeVowels(input);
        out.println("remove  the  vowels:  "+vowels);

    }

    public static String removeVowels(String input) {
        String str = "";
        char[] charArray = input.toCharArray();
        char[] vowelsArray = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        boolean addchar = false;
        for (int i = 0; i < charArray.length; i++) {
            addchar=true;
            for (int j = 0; j < vowelsArray.length; j++) {
                if(vowelsArray[j]== charArray[i]) {
                    addchar=false;

                }

            }
            if(addchar){
                str=str+charArray[i];
            }

        }

        return str ;
    }}

