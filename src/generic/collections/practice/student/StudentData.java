package generic.collections.practice.student;

public class StudentData<K,T> implements Student<K,T>, Comparable<StudentData<K,T>>{
    private K rollNo;
    private T name;
    private int age;
    private String address;

    @Override
    public K getRollNo() {
        return rollNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRollNo(K rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "name=" + name +
                ", rollNo=" + rollNo +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compareTo(StudentData<K, T> o) {
       return Integer.compare(this.age, o.age);
    }
}
