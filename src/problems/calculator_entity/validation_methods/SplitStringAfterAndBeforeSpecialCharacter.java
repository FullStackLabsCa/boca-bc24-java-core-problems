package problems.calculator_entity.validation_methods;

import java.util.StringTokenizer;

public class SplitStringAfterAndBeforeSpecialCharacter {
    public static String[] splitStringAfterAndBeforeSpecialCharacter(String stringWithOutSpace) {
        String[] parts = stringWithOutSpace.split("[+\\-*/]");
        return parts;
    }
}
