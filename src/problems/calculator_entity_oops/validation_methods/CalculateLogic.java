package problems.calculator_entity_oops.validation_methods;

import problems.calculator_entity_oops.operations.Addition;
import problems.calculator_entity_oops.operations.Division;
import problems.calculator_entity_oops.operations.Multiplication;
import problems.calculator_entity_oops.operations.Subtraction;

import java.util.List;

public class CalculateLogic {
    public static String calculationLogic(String result, List<Double> operands, List<Character> operators) {
        return calculateOperations(result, operands, operators);
    }

    private static String calculateOperations(String result, List<Double> operands, List<Character> operators) {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '+' || operators.get(i) == '-' || operators.get(i) == '*'
                    || operators.get(i) == '/') {
                switch (operators.get(0)) {
                    case '/':
                        if (operands.get(1) == 0) {
                            return null;
                        } else {
                            result = Division.division(operands.get(0), operands.get(1));
                        }
                        break;
                    case '*': {
                        result = Multiplication.multiplication(operands.get(0), operands.get(1));
                        break;
                    }
                    case '+': {
                        result = Addition.addition(operands.get(0), operands.get(1));
                        break;
                    }
                    case '-': {
                        result = Subtraction.subtraction(operands.get(0), operands.get(1));
                        break;
                    }
                }
            }
        }
        return result;
    }

}
