package course_exception;

public class StudentAlreadyEnrolledException extends Exception{
    public StudentAlreadyEnrolledException(String studentId){
        super("Error: Student '" + studentId + "'is already enrolled");
    }
}
