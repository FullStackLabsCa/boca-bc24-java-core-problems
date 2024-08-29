package problems.generics;

import java.util.Map;

public class CourseRunner {
    public static void main(String[] args) {

        //To use String as Student id and Integer as Grade
        Course<String,Integer> stringAsStuId = new Course<>();
        stringAsStuId.enrollStudent("Mohan");
        stringAsStuId.enrollStudent("Mohan");
        stringAsStuId.enrollStudent("Kumar",90);
        stringAsStuId.enrollStudent("Ram",95);
        //stringAsStuId.assignGrade("Mohan",92);
        stringAsStuId.listAllGrades();

        //To use Integer as Student id and double as Grade
        Course<Integer,Double> integerAsStuId = new Course<>();
        integerAsStuId.enrollStudent(3);
        integerAsStuId.enrollStudent(2,90.0);
        integerAsStuId.enrollStudent(1,95.0);
        integerAsStuId.assignGrade(3,92.8);
        integerAsStuId.listAllGrades();
        Map<Integer,Double> integerAsStuIdMap = integerAsStuId.getAllStudentsAndGrade();
        for(Integer s : integerAsStuIdMap.keySet()) {
            System.out.println("Student : " + s + "\tGrade : " + integerAsStuIdMap.get(s));
        }
    }
}
