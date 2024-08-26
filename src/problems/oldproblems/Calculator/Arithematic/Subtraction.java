package Calculator.Arithematic;

public class Subtraction {
    private final double a;
    private final double b;

    public Subtraction(String a, String b){
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double subtract(){
        return this.a - this.b;
    }
}

