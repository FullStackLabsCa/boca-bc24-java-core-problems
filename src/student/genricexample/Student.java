package student.genricexample;

public interface Student<K,V>{
    K getRollNo();
    V getName();
    int compareTo(StudentData<K, V> object);
}
