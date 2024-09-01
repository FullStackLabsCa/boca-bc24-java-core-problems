package problems;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorBasic {
    public static void main(String[] args) {
        CalculatorBasic cal = new CalculatorBasic();
        System.out.println(cal.calculateBasic("5 + 5"));
    }

    public String calculateBasic(String exp) {
        String regex = "\\d+[.]?\\d*[-+*/]\\d+[.]?\\d*$";

        String x = validateEmptyOrNull(exp);
        if (x != null) return x;

        String output = "";
        exp = exp.replaceAll("\\s", "");

        String x1 = getInputFormat(exp);
        if (x1 != null) return x1;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exp);
        if (!matcher.find()) {
            return "Error: Invalid number format";
        } else {
            String opRegex = "\\d+[.]?\\d*";
            String operation = exp.replaceAll(opRegex, "");

            switch (operation) {
                case "+":
                    output = addition(exp);
                    break;
                case "-":
                    output = substraction(exp);
                    break;
                case "*":
                    output = multiplication(exp);
                    break;
                case "/":
                    output = division(exp);
//                    System.out.println(output);
                    break;
                default:
                    System.out.println("Try Again");
                    break;

            }
        }

        return output;
    }

    private static String getInputFormat(String exp) {
        String[] parts = exp.split("[\\+\\-\\*/]");
        if (parts.length != 2) {
            return "Error: Invalid input format";
        }
        return null;
    }

    private static String validateEmptyOrNull(String exp) {
        if (exp == null || exp.isEmpty()) {
            return "Error: Input is empty or null";
        }
        return null;
    }

    private String division(String exp) {
        String[] nums = exp.split("/");
        double num1 = Double.parseDouble(nums[0]);
        double num2 = Double.parseDouble(nums[1]);
        if (num2 == 0) {
            return "Error: Cannot divide by zero";
        } else {
            return Double.toString(num1 / num2);
        }
    }

    private String multiplication(String exp) {
        String[] nums = exp.split("\\*");
        double product = Double.parseDouble(nums[0]) * Double.parseDouble(nums[1]);
        return Double.toString(product);
    }

    private String substraction(String exp) {
        String[] nums = exp.split("-");
        double difference = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
        return Double.toString(difference);
    }

    private String addition(String exp) {
        String[] nums = exp.split("\\+");
        double sum = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
        return Double.toString(sum);
    }
}
