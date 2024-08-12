//package problems;
//
//public class Calculator {
//    public static void main(String[] args) {
//        calculate calculate = new calculate();
//
//        boolean exit = true;
//        while (exit) {
//            System.out.println("Enter the operation you want to perform");
//            String input = obj.nextLine();
//            if (input.equals("x")) {
//                exit = false;
//                break;
//            } else {
//                int num1 = calculate.getOperand1(input);
//                int num2 = calculate.getOperand2(input);
//                char op = calculate.getOperator(input);
//                System.out.println(calculate.getAnswer(num1, num2, op));
//            }
//        }
//    }
//
//    public class calculate {
//        int a, b;
//        char operator = '\0';
//
//        public char getOperator(String input) {
//
//            for (char c : input.toCharArray()) {
//                if (c == '+' || c == '*' || c == '/' || c == '-') {
//                    operator = c;
//                }
//            }
//            return operator;
//        }
//
//        public int getOperand1(String input) {
//            String[] operands = input.split("[+\\-*/]");
//            try {
//                a = Integer.parseInt(operands[0]);
//
//            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//                System.out.println("First number is not valid");
//            }
//            return a;
//        }
//        public int getOperand2(String input) {
//            String[] operands = input.split("[+\\-*/]");
//            try {
//                b = Integer.parseInt(operands[1]);
//
//            } catch (NumberFormatException  | ArrayIndexOutOfBoundsException e) {
//                System.out.println("Second number is not valid");
//            }
//            return b;
//        }
//
//        public int getAnswer(int num1,int num2,char op){
//            int ans = 0;
//
//            switch (op) {
//                case '+' -> ans = num1 + num2;
//                case '-' -> ans = num1 - num2;
//                case '*' -> ans = num1 * num2;
//                case '/' -> {
//                    if (num2 == 0) {
//                        System.out.println("Cannot divide by 0");
//                        ans = 0;
//                    } else {
//                        ans = num1 / num2;
//                    }
//                }
//                default -> System.out.println("Invalid");
//            }
//            return ans;
//        }
//
//
//}
//
//}
//
//
//
