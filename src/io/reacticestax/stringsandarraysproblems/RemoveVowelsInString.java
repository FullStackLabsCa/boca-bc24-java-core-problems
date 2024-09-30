package io.reacticestax.stringsandarraysproblems;

public class RemoveVowelsInString {

//    Remove Vowels from a String
//    Objective: Write a method that removes all vowels from a given string.
//            Requirements:
//    The method  removeVowels(String input)  should return the string without any vowels.
//    Example Usage:
//    removeVowels("Hello World")  should return  "Hll Wrld" .
//    removeVowels("Java Programming")  should return  " lv Prgrmmng" .
//    Hints:
//    Use a loop to iterate through the string and build a new string that excludes vowels




    public static String removeVowels(String inputString){


        String[] vowelArray = {"a", "e" , "i", "o", "u"};
        int count=0;
        String[] arr = inputString.split(" ");
        String newString = " ";

        for(String input : arr) {
            for(String vowel : vowelArray){

                if(inputString.contains(vowel)){

               newString = inputString.replaceAll("[aeiou]","");

                }
            }


        }

        return newString;
    }



    
}
