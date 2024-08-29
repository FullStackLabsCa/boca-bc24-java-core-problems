package problems.string;

import java.util.Scanner;

public class RemoveVowels {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        String name= sc.nextLine();
        //String name = "mandeeptuhokkikk";
        int count = 0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u',};
        name= name.toLowerCase();
        for (int i = 0; i < name.length(); i++) {
            for (int j = 0; j < vowels.length; j++) {
                if (vowels[j] == name.charAt(i)) {

                    //count++;
                     // String replaced1 = name.replace('e',' ');
                    name = name.replace("a", "");
                    name = name.replace("e", "");
                    name = name.replace("i", "");
                    name = name.replace("o","");
                    name = name.replace("u", "");

                 count++;

                    System.out.println(name);


                }


            }
        }
    }
}