package problems.calculator_Entity;

public class Subtraction extends Input{
    public Subtraction(float num1, float num2) {
        super(num1, num2);
    }

    @Override
    public String toString() {
        return "Subtraction{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                "} " + super.toString();
    }

    public String subtraction(){
        float sub;
//        if (num1>num2) {
//             sub= num1 - num2;
//        }else {
//           sub= num2 - num1;
//        }
        sub=num1-num2;
        System.out.println("Result is "+sub);
        return String.valueOf(sub);
    }
}
