package problems.stringProblems;

public class CapitalizeFirstLetterOfEachWord {
    public static void main(String[] args) {

        String str = CapitalizeEachWord("I love programming in Java");

        System.out.println("Longest Strng:"+str);
    }

    private static String CapitalizeEachWord(String str) {
        String[] stringArray = str.split(" ");

        String returnString = "";
        for (String word: stringArray){
            char firstCharater = word.charAt(0);
            word = word.replace(firstCharater, Character.toUpperCase(firstCharater));
            returnString = returnString + " " + word;
        }
        return returnString;
    }


}
