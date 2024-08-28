package problems;

public class CalculatorWithMemoryRunner {
    public static void main(String[] args) {
        CalculatorWithMemory.recallMemory();
        System.out.println(CalculatorWithMemory.recallAllMemory());


        Double input1 = CalculatorWithMemory.calculate("-2 * 4*4^3*((sqrt(4)/32+3)*3+5)/(6-8)+4*sqrt(8) M+");
        Double input2 = CalculatorWithMemory.calculate("5+3 +5");
        Double input3 = CalculatorWithMemory.calculate("sqrt(4) M");
        Double input4 = CalculatorWithMemory.calculate("5/2 M+");
        Double input5 = CalculatorWithMemory.calculate("5+4 M+");
        Double input6 = CalculatorWithMemory.calculate("5-3 M+");
        Double input7 = CalculatorWithMemory.calculate("1-1 M+");
        //System.out.println("result = " + result);
        System.out.println(CalculatorWithMemory.recallAllMemory());
        System.out.println(CalculatorWithMemory.recallMemory());
        System.out.println(CalculatorWithMemory.recallAllMemory());
    }
}
