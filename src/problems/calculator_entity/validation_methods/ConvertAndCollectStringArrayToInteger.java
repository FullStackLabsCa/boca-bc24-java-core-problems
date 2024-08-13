package problems.calculator_entity.validation_methods;

import java.util.ArrayList;
import java.util.List;

public class ConvertAndCollectStringArrayToInteger {
    public static List<Double> convertAndCollectStringArrayToInteger(String[] parts) {
        List<Double> operands = new ArrayList<>();
        for (String num : parts) {
            operands.add(Double.parseDouble(num));
        }
        return operands;
    }
}
