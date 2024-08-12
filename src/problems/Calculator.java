package problems;

public class Calculator {

    public String calculate(String expression) {
        String[] tokens = expression.split(" ");

        String operand1Str = tokens[0];
        String operator = tokens[1];
        String operand2Str = tokens[2];

        // Check if the first operand is a valid number
        if (!isNumeric(operand1Str)) {
            return "Error: Invalid number format";
        }

        // Check if the second operand is a valid number
        if (!isNumeric(operand2Str)) {
            return "Error: Invalid number format";
        }

        // Parse the operands
        double operand1 = Double.parseDouble(operand1Str);
        double operand2 = Double.parseDouble(operand2Str);
        double result = 0;

        // Perform the operation based on the operator
        if (operator.equals("+")) {
            result = operand1 + operand2;
        }
        else if(operator.equals("-")){
            result = operand1 - operand2;
        } else if (operator.equals("*")) {
            result = operand1 * operand2;
        } else if (operator.equals("/")) {
            if (operand2 == 0) {
                return "Error: Cannot divide by zero";
            }
            result = operand1 / operand2;
        } else {
        }
        return String.valueOf(result);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
