package problems;

class Addition {
    private final double a;
    private final double b;

    public Addition(String a, String b) {
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double add() {
        return this.a + this.b;
    }
}

class Division {
    private final double a;
    private final double b;

    public Division(String a, String b) {
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double divide() {
        return this.a / this.b;
    }
}

class Multiplication {
    private final double a;
    private final double b;

    public Multiplication(String a, String b) {
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double multiply() {
        return this.a * this.b;
    }
}

class Subtraction {
    private final double a;
    private final double b;

    public Subtraction(String a, String b) {
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
    }

    public double subtract() {
        return this.a - this.b;
    }
}

public class Calculator {
    public String calculate(String str) {
        if (str != null) {
            str = str.replaceAll(" ", "");

            if (str.matches("\\d+[.]?\\d+[+\\-*/]\\d+[.]?\\d+$") || str.matches("\\d+[+\\-*/]\\d+")) {
                if (str.contains("+")) {
                    String[] st = str.split("\\+");
                    Addition add = new Addition(st[0], st[1]);
                    return (Double.toString(add.add()));
                } else if (str.contains("-")) {
                    String[] st = str.split("-");
                    Subtraction sub = new Subtraction(st[0], st[1]);
                    return (Double.toString(sub.subtract()));
                } else if (str.contains("*")) {
                    String[] st = str.split("\\*");
                    Multiplication mul = new Multiplication(st[0], st[1]);
                    return (Double.toString(mul.multiply()));
                } else {
                    String[] st = str.split("/");
                    if (!st[1].contains("0")) {
                        Division div = new Division(st[0], st[1]);
                        return (Double.toString(div.divide()));
                    } else {
                        return ("Error: Cannot divide by zero");
                    }
                }
            } else if (str.equalsIgnoreCase("")) {
                return ("Error: Input is empty or null");
            } else if (str.matches("[a-z]+[+\\-*/][0-9]+$")) {
                return ("Error: Invalid number format");
            } else {
                return ("Error: Invalid input format");
            }
        }
        return ("Error: Input is empty or null");
    }
}
