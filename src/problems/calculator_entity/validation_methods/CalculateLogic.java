package problems.calculator_entity.validation_methods;

import problems.calculator_entity.operations.Addition;
import problems.calculator_entity.operations.Division;
import problems.calculator_entity.operations.Multiplication;
import problems.calculator_entity.operations.Subtraction;

import java.util.List;

public class CalculateLogic {
    public static String calculationLogic(String result, List<Integer> operands, List<Character> operators) {
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
