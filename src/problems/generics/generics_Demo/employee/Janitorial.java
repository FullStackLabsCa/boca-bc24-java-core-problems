package problems.generics.generics_Demo.employee;

public class Janitorial extends Maintainance{

    public Janitorial(String name, String work) {
        super(name, work);
    }

    @Override
    public String toString() {
        return "Janitorial{" +
                "work='" + work + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
