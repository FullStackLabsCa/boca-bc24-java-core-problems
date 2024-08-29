package problems;


import genricproblem.School;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertTrue;

public class SchoolTest {

    private School<Integer, Double> school;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        school = new School<>();
    }

    @Test
    public void testAddCourse() {
        school.processCommand("add_course Math101");
        school.processCommand("add_course Physics102");

        assertTrue(systemOutRule.getLog().contains("Course 'Math101' added."));
        assertTrue(systemOutRule.getLog().contains("Course 'Physics102' added."));
    }

    @Test
    public void testListCourses() {
        school.processCommand("add_course Math101");
        school.processCommand("add_course Physics102");

        systemOutRule.clearLog();
        school.processCommand("list_courses");

        assertTrue(systemOutRule.getLog().contains("Courses offered:"));
        assertTrue(systemOutRule.getLog().contains("Math101"));
        assertTrue(systemOutRule.getLog().contains("Physics102"));
    }

    @Test
    public void testEnrollStudent() {
        school.processCommand("add_course Math101");
        school.processCommand("enroll_student Math101 12345");

        assertTrue(systemOutRule.getLog().contains("Student '12345' enrolled in course 'Math101'."));
    }

    @Test
    public void testEnrollStudentInNonExistentCourse() {
        school.processCommand("enroll_student Physics103 12345");

        assertTrue(systemOutRule.getLog().contains("Error: Cannot enroll student. Course 'Physics103' does not exist."));
    }

    @Test
    public void testAssignGrade() {
        school.processCommand("add_course Math101");
        school.processCommand("enroll_student Math101 12345");
        school.processCommand("assign_grade Math101 12345 85.5");

        assertTrue(systemOutRule.getLog().contains("Grade '85.5' assigned to student '12345' in course 'Math101'."));
    }

    @Test
    public void testAssignGradeToNonEnrolledStudent() {
        school.processCommand("add_course Math101");
        school.processCommand("assign_grade Math101 54321 75");

        assertTrue(systemOutRule.getLog().contains("Error: Cannot assign grade. Student '54321' is not enrolled in course 'Math101'."));
    }

    @Test
    public void testListGrades() {
        school.processCommand("add_course Math101");
        school.processCommand("enroll_student Math101 12345");
        school.processCommand("assign_grade Math101 12345 85.5");

        systemOutRule.clearLog();
        school.processCommand("list_grades Math101");

        assertTrue(systemOutRule.getLog().contains("Student: 12345, Grade: 85.5"));
    }

    @Test
    public void testReportUniqueCourses() {
        school.processCommand("add_course Math101");
        school.processCommand("add_course Physics102");

        systemOutRule.clearLog();
        school.processCommand("report_unique_courses");

        assertTrue(systemOutRule.getLog().contains("Courses offered:"));
        assertTrue(systemOutRule.getLog().contains("Math101"));
        assertTrue(systemOutRule.getLog().contains("Physics102"));
    }

    @Test
    public void testReportUniqueStudents() {
        school.processCommand("add_course Math101");
        school.processCommand("add_course Physics102");
        school.processCommand("enroll_student Math101 12345");
        school.processCommand("enroll_student Physics102 54321");

        systemOutRule.clearLog();
        school.processCommand("report_unique_students");

        assertTrue(systemOutRule.getLog().contains("Unique students enrolled:"));
        assertTrue(systemOutRule.getLog().contains("12345"));
        assertTrue(systemOutRule.getLog().contains("54321"));
    }

    @Test
    public void testReportAverageScore() {
        school.processCommand("add_course Math101");
        school.processCommand("enroll_student Math101 12345");
        school.processCommand("assign_grade Math101 12345 85.5");

        systemOutRule.clearLog();
        school.processCommand("report_average_score Math101");

        assertTrue(systemOutRule.getLog().contains("Average score for course 'Math101': 85.5"));
    }

    @Test
    public void testReportCumulativeAverage() {
        school.processCommand("add_course Math101");
        school.processCommand("add_course Physics102");
        school.processCommand("enroll_student Math101 12345");
        school.processCommand("enroll_student Physics102 12345");
        school.processCommand("assign_grade Math101 12345 85.0");
        school.processCommand("assign_grade Physics102 12345 90.0");

        systemOutRule.clearLog();
        school.processCommand("report_cumulative_average 12345");

        assertTrue(systemOutRule.getLog().contains("Cumulative average score for student '12345': 87.5"));
    }

    @Test
    public void testUnknownCommand() {
        school.processCommand("unknown_command");

        assertTrue(systemOutRule.getLog().contains("Error: Unknown command 'unknown_command'. Please use a valid command."));
    }
}