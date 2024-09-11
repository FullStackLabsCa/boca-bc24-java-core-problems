package schoolWithUserExceptions;

public class GradesNotFoundException extends RuntimeException{
    public GradesNotFoundException(){
        System.out.println("Grades Not found Exception....");
    }
}
