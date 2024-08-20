package Calculator_Entity;

public class Division extends Input {
    public Division(float num1, float num2) {
        super(num1, num2);
    }

    public String division() {
        String s;
        float div;
//        if (num1>num2) {
//            div= num1/num2;
//        }else {
//            div= num2/num1;
//        }
        if (num2!=0) {
            div = num1 / num2;
            System.out.println("Result is " + div);
             s = String.valueOf(div);
        }else s = "Error: Cannot divide by zero";
        return s;
    }
}