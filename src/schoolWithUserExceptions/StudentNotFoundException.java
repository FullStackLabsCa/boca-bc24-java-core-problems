package schoolWithUserExceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException() {
        System.out.println("Student Not found..... ");
    }
}
