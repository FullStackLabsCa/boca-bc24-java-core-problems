package string_problem;

import java.util.Scanner;

public class NumberOfVowels {
    public static int countVowels(String input) {
        int countVowels = 0, countA = 0, countE = 0, countI = 0, countO = 0, countU = 0;

//        char[] inputCharArray = input.toLowerCase().toCharArray();
//        System.out.println(inputCharArray);
        char[][] vowelsCount = {{'a', '0'}, {'e', '0'}, {'i', '0'}, {'o', '0'}, {'u', '0'}};
//        System.out.println();
        System.out.println("Vowels found in String are : ");

        for (int i = 0; i < input.length(); i++) {
//            if (input.charAt(i).equals(input.charAt(i))) {
            switch (input.toLowerCase().charAt(i)) {
                case ('a'):
                    vowelsCount[0][1]++;
                    countA++;
                    if (countA<=1){
                        System.out.print("a ");
                    }
                    break;
                case ('e'):
                    vowelsCount[1][1]++;
                    countE++;
                    if (countE<=1){
                        System.out.print("e ");
                    }
                    break;
                case ('i'):
                    vowelsCount[2][1]++;
                    countI++;
                    if (countI<=1){
                        System.out.print("i ");
                    }
                    break;
                case ('o'):
                    vowelsCount[3][1]++;
                    countO++;
                    if (countO<=1){
                        System.out.print("o ");
                    }
                    break;
                case ('u'):
                    vowelsCount[4][1]++;
                    countU++;
                    if (countU<=1){
                        System.out.print("u ");
                    }
                    break;
//            }else
//                default:
//                    count = 0;
            }
                }
        System.out.print("\n2D Matrix is : \n");
        for (int j = 0; j < vowelsCount.length; j++) {
            for (int k = 0; k < vowelsCount[j].length; k++) {
                System.out.print(vowelsCount[j][k]+" ");
            }
            System.out.print("\n");
        }
        countVowels =countA+countE+countI+countO+countU;
        System.out.println("Number of vowels are : ");
        return countVowels;
    }

    public static void main(String[] args) {
        System.out.println("Count the Number of Vowels in provided String");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.print(NumberOfVowels.countVowels(input));

    }
}