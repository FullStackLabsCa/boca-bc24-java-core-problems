package calculator;

public class Addition {

    public static int performAddition(int[] operands) {
        int sum= 0;
        for(Integer numb: operands) {
            sum += numb;
        }
        System.out.println("Addition is " + sum);
        return sum;
    }
}
