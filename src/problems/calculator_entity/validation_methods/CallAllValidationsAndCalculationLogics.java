package problems.calculator_entity.validation_methods;

import java.util.List;

public class CallAllValidationsAndCalculationLogics {
    public static String callAllValidation(String s) {
        String result = "";
        if ((s == null || s.trim().isEmpty())) {
            System.out.println(result = "Error: Input is empty or null");
            return result;
        }

        AlphabetInputValidation.alphabetInputValidation(s);
        //Validate the String Input is empty or null



        // removing the all spaces in the string input
        String stringWithOutSpace = RemoveSpaceFromString.removeSpacesFromString(s);
        if (stringWithOutSpace == null) return "Error: Invalid number format";

        //Splitting the String and stored into the array by using the regex expression "[+\\-*/]"
        // which spilt the string if we found any special mathematics character in the string.
        String[] parts = SplitStringAfterAndBeforeSpecialCharacter
                .splitStringAfterAndBeforeSpecialCharacter(stringWithOutSpace);

        List<Double> operands = ConvertAndCollectStringArrayToInteger
                .convertAndCollectStringArrayToInteger(parts);

        String validNumbersAreAvailableToPerformOperation = CheckValidNumbersAreAvailableToPerformOperation
                .checkValidNumbersAreAvailableToPerformOperation(operands);
        if (validNumbersAreAvailableToPerformOperation != null) return validNumbersAreAvailableToPerformOperation;

        List<Character> operators = GetOperatorsFromListOfCharacter
                .getOperatorsFromListOfCharacter(stringWithOutSpace);

        //performing the Calculator Operations
        result = CalculateLogic.calculationLogic(result, operands, operators);
        if (result == null) return "Error: Cannot divide by zero";
        return result;
    }


}
