package problems.calculator_entity.validation_methods;

public class InputStringEmptyOrNull {
    public static String inputStringEmptyOrNull(String s) {
        if (s == null || s.trim().isEmpty()) {
            return "Error: Input is empty or null";
        }
        return null;
    }
}
