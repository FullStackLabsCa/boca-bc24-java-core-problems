package problems;

import java.util.Stack;

public class Calculator {
        public static void main(String[] args) {
            String usrStr = "(10 + 2) * 3";
            System.out.println("Expected Result : "+usrStr+" = 4");
            try {
                int result = calculate(usrStr);
                System.out.println(usrStr + " = " + result);
            } catch (Exception e) {
                System.out.println("Error evaluating expression: " + e.getMessage());
                e.printStackTrace();
            }
        }

        static int calculate(String usrStr) throws Exception{
            Stack<Integer> numbers = new Stack<>();
            Stack<Character> operators = new Stack<>();
            Stack<Double> memory = new Stack<>();


            int i = 0;
            while(i<usrStr.length())
            {
                char ch = usrStr.charAt(i);
                if(Character.isDigit(ch)){
                    int num = 0;
                    while(i<usrStr.length() && Character.isDigit(usrStr.charAt(i))){
                        num = num * 10 + (usrStr.charAt(i)-'0');
                        i++;
                    }
                    numbers.push(num);
                } else if (ch == '(') {
                    operators.push(ch);
                    i++;
                } else if (ch == ')') {
                    while(operators.peek()!='('){
                        calculation(operators.pop(),numbers);
//                    System.out.println(ch);
                    }
                    operators.pop();
                    i++;
                } else if (checkOperator(ch)) {
                    while(!operators.isEmpty() && operatorSequence(ch) <= operatorSequence((operators.peek()))){
                        calculation(operators.pop(),numbers);
                    }
                    operators.push(ch);
                    i++;
                }
                else{
                    i++;
                }
            }
            while(!operators.isEmpty()){
                calculation(operators.pop(), numbers);
            }
            if(numbers.size() != 1){
                throw new Exception("Invalid Expression");
            }
            return numbers.pop();
        }


        private static void calculation(char operator, Stack<Integer> numbers) throws Exception{
            int b = numbers.pop();
            int a = numbers.pop();
            switch (operator){
                case '+' :
                    numbers.push(a+b);
                    break;
                case '-' :
                    numbers.push(a-b);
                    break;
                case '*':
                    numbers.push(a*b);
                    break;
                case '/':
                    if(b == 0){
                        throw new ArithmeticException("Division by Zero");
                    }
                    numbers.push(a/b);
                default:
                    throw new Exception("Invalid Exception");
            }
        }

        private static boolean checkOperator(char ch){
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                return true;
            }
            else return false;
        }

        private static int operatorSequence(char operator){
            if(operator == '+' || operator == '-') {
                return 1;
            } else if (operator == '*' || operator == '/') {
                return 2;
            }
            else return -1;
        }
    }
