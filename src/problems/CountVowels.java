package problems;

import java.util.HashMap;
import java.util.Map;

public class CountVowels {
    public static void main(String[] args) {

   System.out.println(countVowels("Hello world"));
//        removeVowel("HE llo");

    }
    public static int countVowels(String input){
        Map<Character,Integer> vowelmap = new HashMap<>();
        char c;
        int totalcount = 0;
        if(input==null|| input.isEmpty()){
            System.out.println("Null or Empty values not allowed");

        }else{
        for(int i=0;i<input.length();i++){
            c = input.toLowerCase().charAt(i);
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){

                System.out.print(c+" ");
                if(vowelmap.containsKey(c)){
                    totalcount++;
                    vowelmap.put(c,totalcount+1);

                }
                else{
                    vowelmap.put(c,1);
                }

            }

        }
        }
        System.out.println();
        System.out.println(vowelmap);
        return totalcount;

    }
//    public static void removeVowel(String input) {
//        String newInput = input.toLowerCase().replace("a","")
//                .replace("e","")
//                .replace("i","")
//                .replace("o","")
//                .replace("u","");
//        System.out.println(newInput);
//        }

}
