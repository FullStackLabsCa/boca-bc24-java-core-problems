package problems.old_assignments.problems.problems;

import java.util.Scanner;

public class DuplicateNumberAndAlphabet {
    //12 Read Abc given string and count how many times Abc given alphabet or number is repeated

//    public  void duplicateNumber(String input, char charEntry){
//        char[] charArray = input.toCharArray();
//        int count = 0;
//        for (int i = 0; i < charArray.length; i++) {
//            if (charArray[i] == charEntry) {
//                count++;
//            }
//        }
//        System.out.println("******************* Below output coming from Method *****************");
//        System.out.println("The given " + charEntry + " is repeated " +count + " times");
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String....");
        String input = scanner.nextLine();
        char[] charArray = input.toCharArray();
        System.out.println("To count the duplicates, Enter the alphabet or number..");
        char charEntry = scanner.nextLine().charAt(0);
        int count = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == charEntry) {
                count++;
            }
        }
        System.out.println("The given " + charEntry + " is repeated " +count + " times");

        DuplicateNumberAndAlphabet duplicateNumberAndAlphabet = new DuplicateNumberAndAlphabet();
       // duplicateNumberAndAlphabet.duplicateNumber("karan", 'a');
    }
}
