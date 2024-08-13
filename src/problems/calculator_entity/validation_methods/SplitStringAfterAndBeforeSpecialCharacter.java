package problems.calculator_entity.validation_methods;

public class SplitStringAfterAndBeforeSpecialCharacter {
    public static String[] splitStringAfterAndBeforeSpecialCharacter(String stringWithOutSpace) {
        String[] parts = stringWithOutSpace.split("[+\\-*/]");
        return parts;
    }
}
