package schoolWithUserExceptions;
public class CourseNotFoundException extends  RuntimeException {
    public CourseNotFoundException() {
        System.out.println("Course Not found");
    }

}
