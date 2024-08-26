package problems.arraysandstrings;

import java.util.Scanner;

public class CountVowels {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to count vowels: ");
        String str = scanner.nextLine();
        CountVowels countVowels = new CountVowels();
        countVowels.countVowels(str);
    }

    public void countVowels(String input){
        int count =0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int[] vowelsCount = new int[5];
        if(!input.isEmpty()){
            for(int i=0; i< input.length(); i++){
                switch (input.toLowerCase().charAt(i)){
                    case 'a':
                        count++;
                        vowelsCount[0]++;
                        break;
                    case 'e':
                        count++;
                        vowelsCount[1]++;
                        break;
                    case 'i':
                        count++;
                        vowelsCount[2]++;
                        break;
                    case 'o':
                        count++;
                        vowelsCount[3]++;
                        break;
                    case 'u':
                        count++;
                        vowelsCount[4]++;
                        break;
                    default:
                }
            }
        }
        System.out.println("Number of vowels in the string: "+ count);
        System.out.println("Count of vowels: ");
        for (int i=0; i<vowelsCount.length; i++){
            System.out.println(vowels[i] +": "+ vowelsCount[i]);
        }
    }
}
