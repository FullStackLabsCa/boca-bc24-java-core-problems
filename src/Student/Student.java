package Student;

public interface Student<K,V>{
    K getRollNo();
    V getName();

    int compareTo(StudentData<K, V> object);
}
