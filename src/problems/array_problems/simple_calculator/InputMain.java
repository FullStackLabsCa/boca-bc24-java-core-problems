package problems.array_problems.simple_calculator;

public class InputMain {
    public static void main(String[] args) {
        InputValidation inputValidation = new InputValidation();
        inputValidation.isValidExpression("(2.5+ 3 -2)");
        inputValidation.areParenthesesBalanced("(2.5+ 3 -2)");
        inputValidation.isExpressionCorrect("(2.5+ 3 -2)");
    }
}
