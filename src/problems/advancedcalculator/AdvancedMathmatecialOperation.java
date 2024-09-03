package problems.advancedcalculator;

public class AdvancedMathmatecialOperation {

    public static double calculateSquareRoot(String input) {
        double inputNumber = Double.parseDouble(input.replaceAll("[^0-9.-]", ""));
        if(inputNumber < 0) {
         throw new ArithmeticException("Cannot calculate the square root of negative numbers");
        }
        return Math.sqrt(inputNumber);
    }

    public static double calculateExponential(String input) {
        String[] split = input.split("\\^");
        double base = Double.parseDouble(split[0].trim());
        double exponent = Double.parseDouble(split[1].trim());
        return Math.pow(base, exponent);
    }
}
