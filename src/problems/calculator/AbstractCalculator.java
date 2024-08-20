package problems.calculator;

public abstract class AbstractCalculator {

    public final static char[] operatorsAvailable = {'+','-','*','/'};

    public boolean validateInput(String expression){
        //If the input is Null or Empty String
        if(expression == null || expression.trim().isEmpty()){
            System.out.println("Illegal Expression!!!");
            return false;
        }
        //If the Input has alphabets
        if(expression.contains(".*[a-zA-Z]+.*")){
            System.out.println("Illegal Arguments!!! Expression Contains Alphabets.");
            return false;
        }
        //Check if the Input have at least 1 operation
        //
        //
        //Other Validations (if Applicable)
        //...
        //Otherwise
        return true;
    }

    public String[] getOperands(String expression){
        //Remove spaces from the Expression
        expression = expression.replaceAll(" ", "");
        //Split on operations and return that
        return expression.split("[-+*/]");
    }

    public char[] getOperators(String expression){
        char[] operatorArrayTemp = new char[expression.length()];
        int operatorCount = 0;
        //Remove spaces from the expression
        expression = expression.replaceAll(" ","");

        char[] expressionChars = expression.toCharArray();

        //Run the for loop and add the operator to array
        for(char eachChar : expressionChars){
            for(char operator : operatorsAvailable){
                if(eachChar == operator){
                    operatorArrayTemp[operatorCount] = eachChar;
                    operatorCount++;
                    break;
                }
            }
        }
        //Remove the extra elements from the array (could be better optimized using Collections)
        char[] operatorArray = new char[operatorCount];
        System.arraycopy(operatorArrayTemp,0,operatorArray,0,operatorCount);

        return operatorArray;
    }

}
