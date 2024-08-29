package problems;

import generic.CourseOne;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class CourseOneTest {

    private CourseOne<Integer, Double> courseOne;

    @Before
    public void setUp() {
        courseOne = new CourseOne<>();
    }

    @Test
    public void testEnrollStudent() {
        courseOne.enrollStudent(12345);
        assertTrue(courseOne.isStudentEnrolled(12345));

        // Attempting to enroll the same student should not change anything
        courseOne.enrollStudent(12345);
        assertEquals(1, courseOne.getAllGrades().size());
    }

    @Test
    public void testAssignGrade() {
        courseOne.enrollStudent(12345);
        courseOne.assignGrade(12345, 95.0);
        assertEquals(Optional.of(95.0), courseOne.getGrade(12345));
    }

    @Test
    public void testAssignGradeToNonEnrolledStudent() {
        courseOne.assignGrade(12345, 95.0);
        assertEquals(Optional.empty(), courseOne.getGrade(12345));
    }

    @Test
    public void testGetGrade() {
        courseOne.enrollStudent(12345);
        courseOne.assignGrade(12345, 95.0);
        Optional<Double> grade = courseOne.getGrade(12345);
        assertTrue(grade.isPresent());
        assertEquals(95.0, grade.get(), 0.01);
    }

    @Test
    public void testGetAllGrades() {
        courseOne.enrollStudent(12345);
        courseOne.assignGrade(12345, 95.0);
        courseOne.enrollStudent(67890);
        courseOne.assignGrade(67890, 88.0);

        Map<Integer, Double> grades = courseOne.getAllGrades();
        assertEquals(2, grades.size());
        assertEquals(Optional.of(95.0), Optional.ofNullable(grades.get(12345)));
        assertEquals(Optional.of(88.0), Optional.ofNullable(grades.get(67890)));
    }

    @Test
    public void testListAllGrades() {
        courseOne.enrollStudent(12345);
        courseOne.assignGrade(12345, 95.0);
        courseOne.enrollStudent(67890);

        courseOne.listAllGrades();

        // Here, you would capture the system output to verify that it matches expected output.
        // This is a basic check to ensure method runs without error.
    }

    @Test
    public void testIsStudentEnrolled() {
        courseOne.enrollStudent(12345);
        assertTrue(courseOne.isStudentEnrolled(12345));
        assertFalse(courseOne.isStudentEnrolled(67890));
    }
}