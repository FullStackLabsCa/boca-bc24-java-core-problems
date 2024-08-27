package problems.old_assignments.demo;

public class B extends A{
    void makeNoise(){
        System.out.println("hahaha");

    }

    public static void main(String[] args) {
        A b = new B();
        B a = new B();
        A c = new A();

        c.display();
        b.display();

        a.makeNoise();
        a.display();
    }


}
