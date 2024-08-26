package problems.array;

public class Calculator {
    private static final String ERROR_MSG = "Error: Invalid number format";

    public static void main(String[] args) {

        System.out.println(calculate("8+5"));
    }
    public static String calculate(String input) {

        if (input == null || input .equalsIgnoreCase("") || input.trim().isEmpty()){
            return "Error: Input is empty or null";
        }else if (!checkExpression(input)){
            String[] parts = input.split("\\s*[+\\-*/]\\s*");
            if (parts.length!=2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()){
                return "Error: Invalid input format";
            }else {
                return ERROR_MSG;
            }
        }else if (checkExpression(input)) {
            return calculateExpression(input);
        }

        return ERROR_MSG ;
    }

    private static String calculateExpression(String input) {
        String[] parts = input.trim().split("\\s*[+\\-*/]\\s*");
        float addendum1 = Integer.parseInt(parts[0]);
        float addendum2 = Integer.parseInt(parts[1]);

        String operator = input.replaceAll("[^+\\-*/]", "").trim();

        switch (operator){
            case "/" :
                if (addendum2 == 0) {
                    return "Error: Cannot divide by zero";
                }
                else {
                    return "" + (addendum1 / addendum2);
                }
            case "*":
                return "" + (addendum1 * addendum2);
            case "+":
                return "" + (addendum1 + addendum2);
            case "-":
                return "" + (addendum1 - addendum2);
            default:

        }
        return ERROR_MSG;
    }

    public static boolean checkExpression(String expr) {
        return expr.matches("^\\s*\\d+\\s*[+\\-*/]\\s*\\d+\\s*$");

    }
}