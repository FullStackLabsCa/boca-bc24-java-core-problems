package problems;

import course.Course;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

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
        assertEquals(Optional.of(95.0), course.getGrade(12345));
    }

    @Test
    public void testAssignGradeToNonEnrolledStudent() {
        course.assignGrade(12345, 95.0);
        assertEquals(Optional.empty(), course.getGrade(12345));
    }

    @Test
    public void testGetGrade() {
        course.enrollStudent(12345);
        course.assignGrade(12345, 95.0);
        Optional<Double> grade = course.getGrade(12345);
        assertTrue(grade.isPresent());
        assertEquals(95.0, grade.get(), 0.01);
    }

    @Test
    public void testGetAllGrades() {
        course.enrollStudent(12345);
        course.assignGrade(12345, 95.0);
        course.enrollStudent(67890);
        course.assignGrade(67890, 88.0);

        Map<Integer, Double> grades = course.getAllGrades();
        assertEquals(2, grades.size());
        assertEquals(Optional.of(95.0), Optional.ofNullable(grades.get(12345)));
        assertEquals(Optional.of(88.0), Optional.ofNullable(grades.get(67890)));
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
