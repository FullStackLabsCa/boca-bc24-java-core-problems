package oldproblems.simpleCalculator;
class Calculator {
    //to validate the input string
    public boolean isValidExpression(String input) {
        // Trim leading and trailing spaces
        input = input.trim();

        // identifying numbers and spaces pattern
        return input.matches("\\d+\\s*[-+*/]\\s*\\d+");
    }

    public double calculate(String input) {
        // Trim and remove spaces from input
        input = input.trim().replaceAll("\\s", "");

        char operator = ' ';
        String[] parts = new String[2];

        if (input.contains("+")) {
            operator = '+';
            parts = input.split("\\+");
        } else if (input.contains("-")) {
            operator = '-';
            parts = input.split("-");
        } else if (input.contains("*")) {
            operator = '*';
            parts = input.split("\\*");
        } else if (input.contains("/")) {
            operator = '/';
            parts = input.split("/");
        }

        // Parse operands
        double operand1 = Double.parseDouble(parts[0]);
        double operand2 = Double.parseDouble(parts[1]);

        // Perform the operation
        switch (operator) {
            case '+': return operand1 + operand2;
            case '-': return operand1 - operand2;
            case '*': return operand1 * operand2;
            case '/': return operand2 != 0 ? operand1 / operand2 : Double.NaN;
            default: return Double.NaN;
        }
    }
}
