package studentgenricexample;
import java.util.*;

public class StudentRunner {
    public static void main(String[] args) {
        StudentData<Integer, String> studentData1 = new StudentData<Integer, String>();
        studentData1.setRollNumber(1);
        studentData1.setStudentName("Joe");
        studentData1.setAge(27);
        studentData1.setDob("2/3/1999");
        studentData1.setAddress("Brampton");

        StudentData<Integer, String> studentData2 = new StudentData<Integer, String>();
        studentData2.setRollNumber(2);
        studentData2.setStudentName("Alexa");
        studentData2.setAge(25);
        studentData2.setDob("12/2/2000");
        studentData2.setAddress("Toronto");

        StudentData<Integer, String> studentData3 = new StudentData<Integer, String>();
        studentData3.setRollNumber(3);
        studentData3.setStudentName("John");
        studentData3.setAge(23);
        studentData3.setDob("5/4/1998");
        studentData3.setAddress("Mississauga");

        System.out.println("--------------Calling with ArrayList ----------");
        List<StudentData> objects = new ArrayList<>();   // print value with Arraylist
        objects.add(studentData1);
        objects.add(studentData2);
        objects.add(studentData3);
        System.out.println("Student Information"+objects);

        System.out.println("--------------Calling with Hash Map ----------");
        Map<Integer, Student> mapStudent = new HashMap<>(); // print value with HashMap
        mapStudent.put(1, studentData1);
        mapStudent.put(2, studentData2);
        mapStudent.put(3, studentData3);
        System.out.println(mapStudent);


        System.out.println("--------------Calling with Hash Set ----------");
        Set<StudentData<Integer, String>> setStudent = new HashSet<>(); //Calling with HashSet
        setStudent.add(studentData1);
        setStudent.add(studentData2);
        setStudent.add(studentData3);
        setStudent.add(studentData2);
        System.out.println(setStudent);


        System.out.println("--------------Calling with Tree Map ----------");
        Map<Integer, Student> treeStudent = new TreeMap<>(); // calling with Tree Map
        treeStudent.put(1, studentData1);
        treeStudent.put(3, studentData3);
        treeStudent.put(2, studentData2);
        System.out.println(treeStudent);

        System.out.println("--------------Calling with Tree Set ----------");
        Set<StudentData<Integer, String>> treesetStudent = new TreeSet<>();
        treesetStudent.add(studentData1);
        treesetStudent.add(studentData2);
        treesetStudent.add(studentData3);
        System.out.println("treesetStudent = " + treesetStudent);
    }
}
