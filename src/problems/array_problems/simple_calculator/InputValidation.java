package problems.array_problems.simple_calculator;

public class InputValidation {
    public static boolean isValidExpression(String expression) {
        String regex = "^[0-9\\+\\-*/()\\s]+$";
        return expression.matches(regex);
    }

    public static boolean areParenthesesBalanced(String expression) {
        int balance = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
                if (balance < 0) {
                    return false; // Too many closing parentheses
                }
                System.out.println("Expression is valid");
            }
        }
        return balance == 0; // Balanced if balance is zero
    }

    public static boolean isExpressionCorrect(String expression) {
        String trimmed = expression.replaceAll("\\s+", "");
        if (!isValidExpression(trimmed)) {
            return false;
        }
        else if (!areParenthesesBalanced(trimmed)) {
            return false;
        }
        else {
            InputValidation inputValidation = new InputValidation();
            inputValidation.performOperation(expression);
        }
        return true;
    }

    private void performOperation(String expression) {
        for (char ch : expression.toCharArray()) {
            System.out.println(ch);
        }
    }
}
