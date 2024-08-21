package problems;

public class Capitalize {
    public static void main(String[] args) {
        firstCapital("hello n world from me");
    }
    public static void firstCapital(String input){
        int count =0;
        String capString="";
        String[] newString;
        newString = input.split(" ");
        for(String i: newString){
           capString += i.substring(0,1).toUpperCase()+i.substring(1)+" ";
        }
        System.out.println(capString);
    }
}
