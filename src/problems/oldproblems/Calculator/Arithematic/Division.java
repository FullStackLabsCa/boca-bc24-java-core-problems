package Calculator.Arithematic;

public class Division {
    private final double a;
    private final double b;

    public Division(String a, String b){
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double divide(){
        return this.a / this.b;
    }
}
