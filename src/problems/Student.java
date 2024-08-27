package problems;

public class Student implements Comparable<Student> {
    String name;
    String address;
    int rollNo;
    String Subject;

    public Student(String name, String address, int rollNo, String subject) {
        this.name = name;
        this.address = address;
        this.rollNo = rollNo;
        Subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rollNo=" + rollNo +
                ", Subject='" + Subject + '\'' +
                '}';
    }


    @Override
    public int compareTo(Student that) {
        if (this.name == null && that.name == null) {
            // pass
        } else if (this.name == null) {
            return -1;
        } else if (that.name == null) {
            return 1;
        } else {
            int nameComparison = this.name.compareTo(that.name);
            if (nameComparison != 0) {
                return nameComparison < 0 ? -1 : 1;
            }
        }

        if (this.rollNo != that.rollNo) {
            return (this.rollNo < that.rollNo ? -1 : 1);
        }

        return 0;
    }
}
