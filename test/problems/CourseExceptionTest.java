package problems;

import course_exception.Course;
import course_exception.GradeNotAssignedException;
import course_exception.StudentAlreadyEnrolledException;
import course_exception.StudentNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CourseExceptionTest {
    private Course<String, Double> course;

    @Before
    public void setUp() {
        course = new Course<>();
    }

    // Test successful student enrollment
    @Test
    public void testEnrollStudent() throws StudentAlreadyEnrolledException {
        course.enrollStudent("Student1");
        assertNotNull(course.getAllGrades().containsKey("Student1"));
    }

    // Test enrolling the same student twice should throw exception
    @Test(expected = StudentAlreadyEnrolledException.class)
    public void testEnrollStudent_AlreadyEnrolled() throws StudentAlreadyEnrolledException {
        course.enrollStudent("Student1");
        course.enrollStudent("Student1"); // This should throw StudentAlreadyEnrolledException
    }

    // Test assigning a grade to an enrolled student
    @Test
    public void testAssignGrade() throws StudentAlreadyEnrolledException, StudentNotFoundException, StudentNotFoundException {
        course.enrollStudent("Student1");
        course.assignGrade("Student1", 85.5);
        assertEquals(Double.valueOf(85.5), course.getAllGrades().get("Student1"));
    }

    // Test assigning a grade to a non-enrolled student should throw exception
    @Test(expected = StudentNotFoundException.class)
    public void testAssignGrade_StudentNotFound() throws StudentNotFoundException {
        course.assignGrade("Student2", 90.0); // This should throw StudentNotFoundException
    }

    // Test retrieving a grade for a student
    @Test
    public void testGetGrade() throws StudentAlreadyEnrolledException, StudentNotFoundException, GradeNotAssignedException {
        course.enrollStudent("Student1");
        course.assignGrade("Student1", 88.0);
        assertEquals(Double.valueOf(88.0), course.getGrade("Student1"));
    }

    // Test retrieving a grade for a non-enrolled student should throw exception
    @Test(expected = StudentNotFoundException.class)
    public void testGetGrade_StudentNotFound() throws StudentNotFoundException, GradeNotAssignedException {
        course.getGrade("Student2"); // This should throw StudentNotFoundException
    }

    // Test retrieving a grade for an enrolled student who hasn't been assigned a grade should throw exception
    @Test(expected = GradeNotAssignedException.class)
    public void testGetGrade_GradeNotAssigned() throws StudentAlreadyEnrolledException, StudentNotFoundException, GradeNotAssignedException {
        course.enrollStudent("Student1");
        course.getGrade("Student1"); // This should throw GradeNotAssignedException
    }

    // Test listing all grades
    @Test
    public void testGetAllGrades() throws StudentAlreadyEnrolledException, StudentNotFoundException {
        course.enrollStudent("Student1");
        course.enrollStudent("Student2");
        course.assignGrade("Student1", 75.0);
        course.assignGrade("Student2", 85.0);

        Map<String, Double> allGrades = course.getAllGrades();
        assertEquals(Double.valueOf(75.0), allGrades.get("Student1"));
        assertEquals(Double.valueOf(85.0), allGrades.get("Student2"));
    }

}
