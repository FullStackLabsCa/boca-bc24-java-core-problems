package Calculator.Arithematic;

public class Multiplication {
    private final double a;
    private final double b;

    public Multiplication(String a, String b){
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double multiply(){
        return this.a * this.b;
    }
}

