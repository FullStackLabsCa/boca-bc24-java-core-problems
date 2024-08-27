package problems;

import java.util.Map;

public class CourseRunner {
    public static void main(String[] args) {

        //To use String as Student id and Integer as Grade
        GenericCourse<String,Integer> stringAsStuId = new GenericCourse<>();
        stringAsStuId.enrollStudent("Mohan");
        stringAsStuId.enrollStudent("Mohan");
        stringAsStuId.enrollStudent("Kumar",90);
        stringAsStuId.enrollStudent("Ram",95);
        stringAsStuId.setGradeforAStuent("Mohan",92);
        stringAsStuId.displayAllStudentsAndGrade();

        //To use Integer as Student id and double as Grade
        GenericCourse<Integer,Double> integerAsStuId = new GenericCourse<>();
        integerAsStuId.enrollStudent(3);
        integerAsStuId.enrollStudent(2,90.5);
        integerAsStuId.enrollStudent(1,95.0);
        integerAsStuId.setGradeforAStuent(3,92.8);
        integerAsStuId.displayAllStudentsAndGrade();
        Map<Integer,Double> integerAsStuIdMap = integerAsStuId.getAllStudentsAndGrade();
        for(Integer s : integerAsStuIdMap.keySet()) {
            System.out.println("Student : " + s + "\tGrade : " + integerAsStuIdMap.get(s));
        }
    }
}
