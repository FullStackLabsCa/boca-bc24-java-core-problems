package StudentGenricExample;

public class StudentData<K,V> implements Student<K,V>, Comparable<StudentData<K,V>> {
    private K RollNumber;
    private V StudentName;
    private int age;
    private String Dob;
    private String Address;

    public void setRollNumber(K rollNumber) {
        RollNumber = rollNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudentName(V studentName) {
        StudentName = studentName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
    @Override
    public String toString() {
        return " StudentData " +
                "\n RollNumber : " + RollNumber +
                ",\n StudentName : " + StudentName +
                ", \n age :" + age +
                ",\n Dob :" + Dob + '\'' +
                ", \n Address :" + Address + '\''
                ;
    }

    @Override
    public K getRollNo() {
        return RollNumber;
    }

    @Override
    public V getName() {
        return StudentName;
    }
    @Override
    public int compareTo(StudentData<K, V> object) {
        return Integer.compare(this.age ,object.age);
    }
}
