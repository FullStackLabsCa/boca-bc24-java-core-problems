package problems;

import java.util.List;
import java.util.Scanner;

public class Subtraction {
    public static void main(String[] args) {
        int num1, num2, result;
        boolean negative;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Operand 1 : ");
        num1 = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter Operand 2 : ");
        num2 = Integer.valueOf(scanner.nextLine());
        //find the big number
        negative = (num2 > num1) ? true : false;
        if (negative) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        List<Integer> num1Array = SubractionUtilityClass.convertIntToArray(num1);
        List<Integer> num2Array = SubractionUtilityClass.convertIntToArray(num2);
        //to equate size of list2 and list 1
        if (num2Array.size() < num1Array.size()) {
            num2Array = SubractionUtilityClass.arraySizeEquaotor(num1Array, num2Array);
        }
        int multiplier = 1, tempResult = 0;
        for (int i = num1Array.size() - 1; i >= 0; i--) {
            if (num1Array.get(i) < num2Array.get(i)) {
                num1Array.set(i, (num1Array.get(i) + 10));
                for (int j = i - 1; j >= 0; j--) {
                    if (num1Array.get(j) > 0) {
                        num1Array.set(j, num1Array.get(j) - 1);
                        break;
                    }
                }
            }
            multiplier = (i == (num1Array.size() - 1)) ? 1 : (multiplier * 10);
            tempResult = (multiplier * (num1Array.get(i) - num2Array.get(i))) + tempResult;
        }
        result = (negative) ? (tempResult * -1) : tempResult;
        System.out.println("result = " + result);
    }
}
