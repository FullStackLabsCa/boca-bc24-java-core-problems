package problems.calculator_entity_oops.validation_methods;

import java.util.ArrayList;
import java.util.List;

public class GetOperatorsFromListOfCharacter {
    public static List<Character> getOperatorsFromListOfCharacter(String stringWithOutSpace) {
        List<Character> operators = new ArrayList<>();
        for (char c : stringWithOutSpace.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operators.add(c);
            }
        }
        return operators;
    }
}
