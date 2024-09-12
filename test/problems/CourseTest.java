package problems;

import org.junit.Before;
import org.junit.Test;
import problems.generics.p4_Generic_Course_Class.Course;
//import problems.generics.databaseCourse.Course;

import java.util.*;

import static org.junit.Assert.*;

public class CourseTest {

    private Course<Integer, Double> course;

    @Before
    public void setUp() {
        course = new Course<>();
    }

    @Test
    public void testEnrollStudent() {
        course.enrollStudent(12345);
        assertTrue(course.isStudentEnrolled(12345));

        // Attempting to enroll the same student should not change anything
        course.enrollStudent(12345);
        assertEquals(1, course.getAllGrades().size());
    }

    @Test
    public void testAssignGrade() {
        course.enrollStudent(12345);
        course.assignGrade(12345, 95.0);
        assertEquals(Double.valueOf(95.0),course.getAllGrades(12345) );
    }

    @Test
    public void testAssignGradeToNonEnrolledStudent() {
        course.assignGrade(12345, 95.0);
        assertEquals(null, course.getAllGrades(12345));
    }

    @Test
    public void testGetGrade() {
        Course<Integer, Integer> course = new Course<>();
        course.enrollStudent(12345);
        course.assignGrade(12345, Integer.valueOf(95));
        Integer grade = course.getAllGrades(12345);
        assertEquals(Integer.valueOf(95), grade);
    }

    @Test
    public void testGetAllGrades() {
        course.enrollStudent(12345);
        course.assignGrade(12345, 95.0);
        course.enrollStudent(67890);
        course.assignGrade(67890, 88.0);

        List<Map.Entry<Integer, Double>> grades = course.getAllGrades();
        assertEquals(2, grades.size());
        assertEquals(grades.get(0).getKey(),Integer.valueOf(12345));
    }

    @Test
    public void testListAllGrades() {
        course.enrollStudent(12345);
        course.assignGrade(12345, 95.0);
        course.enrollStudent(67890);

        course.listAllGrades();

        // Here, you would capture the system output to verify that it matches expected output.
        // This is a basic check to ensure method runs without error.
    }

    @Test
    public void testIsStudentEnrolled() {
        course.enrollStudent(12345);
        assertTrue(course.isStudentEnrolled(12345));
        assertFalse(course.isStudentEnrolled(67890));
    }
}
