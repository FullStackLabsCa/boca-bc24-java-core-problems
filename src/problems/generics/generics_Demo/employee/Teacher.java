package problems.generics.generics_Demo.employee;

public class Teacher extends Employee{

    String subject;

    public Teacher(String name, String subject) {
        super(name);
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
