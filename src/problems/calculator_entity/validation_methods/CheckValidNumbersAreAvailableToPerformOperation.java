package problems.calculator_entity.validation_methods;

import java.util.List;

public class CheckValidNumbersAreAvailableToPerformOperation {
    public static String checkValidNumbersAreAvailableToPerformOperation(List<Double> operands) {
        if (operands.size() == 1){
            return "Error: Invalid input format";
        }
        return null;
    }
}
