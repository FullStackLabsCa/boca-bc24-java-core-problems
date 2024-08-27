package generic_problems.school;

public class SchoolLauncher {
    public static void main(String[] args) {
        System.out.println("School is invoked");
        School<String, Double > BSFSchool = new School<>();
        BSFSchool.addCourse("Java");
        BSFSchool.addCourse("English");
        BSFSchool.addCourse("Hindi");
        BSFSchool.listOfCourses();
        BSFSchool.enrollStudent("Java","Gurpreet");
        BSFSchool.enrollStudent("English","Gurpreet");
        BSFSchool.enrollStudent("Hindi","Gurpreet");
        BSFSchool.enrollStudent("Java","Karan");
        BSFSchool.enrollStudent("English","Karan");
        BSFSchool.enrollStudent("Java","Akshat");
        BSFSchool.listOfStudents();
        BSFSchool.assignGrade("Java","Gurpreet",88.8);
        BSFSchool.assignGrade("Java","Karan",90.8);
        BSFSchool.assignGrade("Java","Akshat",98.8);
        BSFSchool.averageScoreInCourse("Java");
        BSFSchool.studentAverageGrade("Gurpreet");
    }
}