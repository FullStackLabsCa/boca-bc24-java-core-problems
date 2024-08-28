package problems.generics.generics_Demo.employee;

public class Admin extends Employee{

    public Admin(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                '}';
    }
}
