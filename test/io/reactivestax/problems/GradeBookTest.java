package io.reactivestax.problems;

import io.reacticestax.genericsproblems.GradeBook;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GradeBookTest {
    private GradeBook<Double> doubleGradeBook;
    private GradeBook<Integer> integerGradeBook;

    @Before
    public void setUp() {
        doubleGradeBook = new GradeBook<>();
        integerGradeBook = new GradeBook<>();
    }

    // Tests for Double GradeBook
    @Test
    public void testAddDoubleGrade() {
        doubleGradeBook.addGrade(85.0);
        doubleGradeBook.addGrade(90.0);

        assertEquals(2, doubleGradeBook.getNumberOfGrades());
    }

    @Test
    public void testCalculateDoubleAverage() {
        doubleGradeBook.addGrade(85.0);
        doubleGradeBook.addGrade(90.0);
        doubleGradeBook.addGrade(95.0);

        assertEquals("Average grade: 90.0", doubleGradeBook.calculateAverage());
    }

    @Test
    public void testCalculateDoubleAverageWithNoGrades() {
        assertEquals("No grades available to calculate the average.", doubleGradeBook.calculateAverage());
    }

    @Test
    public void testFindHighestDoubleGrade() {
        doubleGradeBook.addGrade(85.0);
        doubleGradeBook.addGrade(90.0);
        doubleGradeBook.addGrade(95.0);

        assertEquals("Highest grade: 95.0", doubleGradeBook.findHighestGrade());
    }

    @Test
    public void testFindHighestDoubleGradeWithNoGrades() {
        assertEquals("No grades available to find the highest grade.", doubleGradeBook.findHighestGrade());
    }

    @Test
    public void testFindLowestDoubleGrade() {
        doubleGradeBook.addGrade(85.0);
        doubleGradeBook.addGrade(90.0);
        doubleGradeBook.addGrade(95.0);

        assertEquals("Lowest grade: 85.0", doubleGradeBook.findLowestGrade());
    }

    @Test
    public void testFindLowestDoubleGradeWithNoGrades() {
        assertEquals("No grades available to find the lowest grade.", doubleGradeBook.findLowestGrade());
    }

    // Tests for Integer GradeBook
    @Test
    public void testAddIntegerGrade() {
        integerGradeBook.addGrade(85);
        integerGradeBook.addGrade(90);

        assertEquals(2, integerGradeBook.getNumberOfGrades());
    }

    @Test
    public void testCalculateIntegerAverage() {
        integerGradeBook.addGrade(85);
        integerGradeBook.addGrade(90);
        integerGradeBook.addGrade(95);

        assertEquals("Average grade: 90.0", integerGradeBook.calculateAverage());
    }

    @Test
    public void testCalculateIntegerAverageWithNoGrades() {
        assertEquals("No grades available to calculate the average.", integerGradeBook.calculateAverage());
    }

    @Test
    public void testFindHighestIntegerGrade() {
        integerGradeBook.addGrade(85);
        integerGradeBook.addGrade(90);
        integerGradeBook.addGrade(95);

        assertEquals("Highest grade: 95", integerGradeBook.findHighestGrade());
    }

    @Test
    public void testFindHighestIntegerGradeWithNoGrades() {
        assertEquals("No grades available to find the highest grade.", integerGradeBook.findHighestGrade());
    }

    @Test
    public void testFindLowestIntegerGrade() {
        integerGradeBook.addGrade(85);
        integerGradeBook.addGrade(90);
        integerGradeBook.addGrade(95);

        assertEquals("Lowest grade: 85", integerGradeBook.findLowestGrade());
    }

    @Test
    public void testFindLowestIntegerGradeWithNoGrades() {
        assertEquals("No grades available to find the lowest grade.", integerGradeBook.findLowestGrade());
    }
}