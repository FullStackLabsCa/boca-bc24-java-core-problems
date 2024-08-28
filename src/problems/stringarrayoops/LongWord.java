package problems.stringarrayoops;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LongWord {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String str = longestWord("I love programming in Jav");
        System.out.println("Longest Strng:"+str);
    }

    public static String longestWord(String str) {
        int len = 0;
        String longestPart="";
          String[] stringArray = str.split(" ");

          for(String part : stringArray){
              if (len<part.length()){
                  longestPart = part;
                  len = part.length();
              }
          }

            return longestPart;

    }



}