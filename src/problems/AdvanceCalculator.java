package problems;

import java.util.Stack;

public class AdvanceCalculator {
    public static void main(String[] args) {
        String usrStr = "2*(3-2)+(5-)";
        System.out.println("Expected Result : "+usrStr+" = 4");
        try {
            System.out.println(usrStrToStack(usrStr));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int usrStrToStack(String usrStr) throws Exception{
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while(i<usrStr.length())
        {
            char ch = usrStr.charAt(i);
            if(Character.isDigit(ch)){
                int num = 0;
                while(i<usrStr.length() && Character.isDigit(usrStr.charAt(i))){
                    num *= 10 + (usrStr.charAt(i) - '0');
                    i++;
                }
                numbers.push(num);
            }
        }
        return numbers.size();
    }
}
