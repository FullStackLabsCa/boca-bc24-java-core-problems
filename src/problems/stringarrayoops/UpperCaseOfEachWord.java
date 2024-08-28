package problems.stringarrayoops;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class UpperCaseOfEachWord {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String str = longestWord("I love programming in Jav");
        System.out.println("Longest Strng:"+str);
    }

    public static String longestWord(String str) {
        int count = 0;
        String longestPart="";
          String[] stringArray = str.split(" ");
          String returnString = "";
          for(String word : stringArray){
              char firctChracter = word.charAt(0);
              word = word.replace(firctChracter,Character.toUpperCase(firctChracter));

              returnString  = returnString + " "+word;
              }


            return returnString;

    }



}