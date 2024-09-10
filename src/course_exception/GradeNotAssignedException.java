package course_exception;

public class GradeNotAssignedException extends Exception {
    public GradeNotAssignedException(String studentId) {
        super("Error: Grade not assigned for student '" + studentId + "'.");
    }
}