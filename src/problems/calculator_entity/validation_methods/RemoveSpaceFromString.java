package problems.calculator_entity.validation_methods;

public class RemoveSpaceFromString {
    public static String removeSpacesFromString(String s) {
        String stringWithOutSpace = s.replaceAll("\\s", "");
        if (s.chars().anyMatch(Character::isLetter)) {
            return null;
        }
        return stringWithOutSpace;
    }
}
