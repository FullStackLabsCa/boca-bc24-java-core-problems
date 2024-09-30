package io.reacticestax.stringsandarraysproblems;
//Capitalize the First Letter of Each Word
//Objective: Write a method that capitalizes the first letter of each word in a string.
//Requirements:
//The method  capitalizeWords(String input)  should return the string with each word's first
//letter capitalized.
//Example Usage:
//capitalizeWords("hello world")  should return  "Hello World" .
//capitalizeWords("java programming language")  should return  "Java Programming
//Language" .
//Hints:

//Split the string into words, capitalize each word, and then join them back together.

public class CapitalFirstWordOfString {


    static String capitalizedString = "";

    public static String capitalizeWord(String inputString) {


        StringBuilder capitalizedString = new StringBuilder();
        String[] arrOfWords = inputString.split(" ");

        for (int i = 0; i < arrOfWords.length; i++) {
            char capitalizedLetter = Character.toUpperCase(arrOfWords[i].charAt(0));
            String capitalizedWord = capitalizedLetter + arrOfWords[i].substring(1);

            arrOfWords[i] = capitalizedWord;
            capitalizedString.append(capitalizedWord);
            if (i < arrOfWords.length - 1) {
                capitalizedString.append(" ");
            }


        }
            System.out.println("new capitalizedString : " + capitalizedString);

        return capitalizedString.toString();
    }

}
