package problems.generics.generics_Demo.employee;

public class Maintainance extends Employee {

    String work;

    public Maintainance(String name, String work) {
        super(name);
        this.work = work;
    }

    @Override
    public String toString() {
        return "Maintainance{" +
                "work='" + work + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

