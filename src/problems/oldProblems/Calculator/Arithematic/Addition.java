package Calculator.Arithematic;

public class Addition {
    private final double a;
    private final double b;

    public Addition(String a, String b){
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double add(){
        return this.a + this.b;
    }
}
