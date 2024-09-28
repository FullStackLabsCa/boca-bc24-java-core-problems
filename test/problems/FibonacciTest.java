package problems;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class FibonacciTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testGenerateFibonacciFor47Numbers() {
        List<Integer> expectedSeries = Arrays.asList(
                0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
                6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040,
                1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986,
                102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903
        );
        List<Integer> actualSeries = Fibonacci.generateFibonacci(47);
        assertEquals(expectedSeries, actualSeries);
    }

    @Test
    public void testGenerateFibonacci() {
        List<Integer> expectedSeries = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377);
        List<Integer> actualSeries = Fibonacci.generateFibonacci(15);
        assertEquals(expectedSeries, actualSeries);
    }

    @Test
    public void testInvalidStringInput() {
        systemInMock.provideLines("abc");
        Fibonacci.main(null);
        assertTrue(systemOutRule.getLog().contains("Invalid input. Please enter a valid number between 4 and 47."));
    }

    @Test
    public void testOutOfRangeInputTooSmall() {
        systemInMock.provideLines("3");
        Fibonacci.main(null);
        assertTrue(systemOutRule.getLog().contains("Invalid input. Please enter a valid number between 4 and 47."));
    }

    @Test
    public void testOutOfRangeInputTooLarge() {
        systemInMock.provideLines("50");
        Fibonacci.main(null);
        assertTrue(systemOutRule.getLog().contains("Invalid input. Please enter a valid number between 4 and 47."));
    }

    @Test
    public void testValidInput() {
        systemInMock.provideLines("4");
        Fibonacci.main(null);
        assertTrue(systemOutRule.getLog().contains("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]"));
    }

    @Test
    public void testNegativeNumberInput() {
        systemInMock.provideLines("-5");
        Fibonacci.main(null);
        assertTrue(systemOutRule.getLog().contains("Invalid input. Please enter a valid number between 4 and 47."));
    }

}
