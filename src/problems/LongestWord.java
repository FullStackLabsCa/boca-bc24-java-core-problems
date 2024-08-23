package problems;

public class LongestWord {
    public static void main(String[] args) {
        findLongestWord("I love  in Java ");
    }
    public static void findLongestWord(String input){
        String longestString="";
       String[] newString;
        newString = input.split(" ");
        for(String i: newString){
            if(i.length()>longestString.length()){
                longestString = i;
            }

        }
        System.out.println(longestString);
    }
}
