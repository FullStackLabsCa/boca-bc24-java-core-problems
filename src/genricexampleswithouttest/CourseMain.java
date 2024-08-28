package genricexampleswithouttest;

public class CourseMain {
    public static void main(String[] args) {
        CourseGeneric<Integer, Integer> courseGeneric = new CourseGeneric<>();
        courseGeneric.enrollStudent(100);
        courseGeneric.enrollStudent(101);
        courseGeneric.enrollStudent(102);
        courseGeneric.assigningGrades(101, 4);
        courseGeneric.assigningGrades(102, 5);
        courseGeneric.retrivingGrades(101);
        System.out.println("student with " + courseGeneric.retrivingGrades(101) + " grades");
        courseGeneric.getlist();
    }
}
