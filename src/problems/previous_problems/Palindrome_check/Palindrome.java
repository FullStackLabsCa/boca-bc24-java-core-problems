package oldproblems.Palindrome_check;

public class Palindrome {
    String inputStr;
    String rev = "";
    boolean match = false;
    Palindrome(String str){
        this.inputStr = str;

        int length = inputStr.length();
        inputStr = inputStr.toLowerCase();
        for(int i = length-1; i>=0; i--){
            rev = rev + inputStr.charAt(i);
        }
        if(inputStr.equals(rev)){
            match =true;
            System.out.println("This string is Palindrome");
        }else{
            System.out.println("This string is not Palindrome");
        }
    }
}
