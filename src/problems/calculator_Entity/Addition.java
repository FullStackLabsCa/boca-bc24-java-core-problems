package problems.calculator_Entity;

public class Addition extends Input {
    public Addition(float num1, float num2) {
        super(num1, num2);
    }

    public String addition(){
        float add = num1+num2;
        System.out.println("Result is "+add);
        return String.valueOf(add);
    }
}
