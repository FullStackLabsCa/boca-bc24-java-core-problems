package problems.calculator_entity.validation_methods;

import java.util.ArrayList;
import java.util.List;

public class ConvertAndCollectStringArrayToInteger {
    public static List<Integer> convertAndCollectStringArrayToInteger(String[] parts) {
        List<Integer> operands = new ArrayList<>();
        for (String num : parts) {
            operands.add(Integer.parseInt(num));
        }
        return operands;
    }
}
