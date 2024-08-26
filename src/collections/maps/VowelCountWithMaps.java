package collections.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VowelCountWithMaps {

    // Solve vowel count problem with MAPS
    public static Map<Character, Integer> countVowels(String input){

        Map<Character, Integer> vowelCountMap = new HashMap<>();

        //Adding vowels to the map with 0 repetitions each
        vowelCountMap.put('a',0);
        vowelCountMap.put('e',0);
        vowelCountMap.put('i',0);
        vowelCountMap.put('o',0);
        vowelCountMap.put('u',0);

        // Validate Input
        if(validateInput(input)){
            //Perform Vowel Count Operation
            char[] charactersInInput = input.toLowerCase().toCharArray();

            for(char character : charactersInInput){
                //Check if the character is a vowel
                if(vowelCountMap.containsKey(character)){
                    // If it is a vowel, get its repetitions
                   int valueOfChar = vowelCountMap.get(character);
                    //Increment the repetitions by 1
                   valueOfChar++;
                   //Update the value of the key (vowel)
                   vowelCountMap.put(character, valueOfChar);
                }
            }
        } else {
            return new HashMap<>();
        }

        return vowelCountMap;
    }



    public static boolean validateInput(String input){
        // Check if the string is Null or Empty string
        if(input==null || input.trim().isEmpty()){
            System.out.println("Invalid Input!!! String in Null or Empty.");
            return false;
        }
        // Other Conditional Checks if required..
        // .
        // .

        return true;
    }

    public static void main(String[] args) {

        System.out.println(VowelCountWithMaps.countVowels("AKshat ooo "));

        List<String> listofSomething = new ArrayList<>();
    }


}
