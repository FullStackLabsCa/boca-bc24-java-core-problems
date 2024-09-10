package course_exception;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String studentId) {
        super("Error: Student '" + studentId + "' is not enrolled in the course.");
    }
}
