package problems.calculator_entity_oops.validation_methods;

public class SplitStringAfterAndBeforeSpecialCharacter {
    public static String[] splitStringAfterAndBeforeSpecialCharacter(String stringWithOutSpace) {
        String[] parts = stringWithOutSpace.split("[+\\-*/]");
        return parts;
    }
}
