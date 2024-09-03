package problems;


import org.junit.Before;
import org.junit.Test;
import problems.oldcalculator.CalculatorG;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Before
    public void setUp() {
        CalculatorG.clearMemory();
    }

    @Test
    public void testBasicOperations() {
        assertEquals(5.6, CalculatorG.calculate("3.5 + 2.1"), 0.001);
        assertEquals(6.0, CalculatorG.calculate("10 - 4"), 0.001);
        assertEquals(42.0, CalculatorG.calculate("6 * 7"), 0.001);
        assertEquals(4.0, CalculatorG.calculate("8 / 2"), 0.001);
    }

    @Test
    public void testDivisionByZero() {
        assertEquals(Double.NaN, CalculatorG.calculate("10 / 0"), 0.001);
    }

    @Test
    public void testMultipleOperations() {
        assertEquals(11.0, CalculatorG.calculate("3 + 5 * 2 - 4 / 2"), 0.001);
    }

    @Test
    public void testParentheses() {
        assertEquals(6.0, CalculatorG.calculate("3 + (2 * 4) - 5"), 0.001);
        assertEquals(36.0, CalculatorG.calculate("(10 + 2) * 3"), 0.001);
    }

    @Test
    public void testAdvancedOperations() {
        assertEquals(4.0, CalculatorG.calculate("sqrt(16)"), 0.001);
        assertEquals(8.0, CalculatorG.calculate("2 ^ 3"), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testSquareRootOfNegativeNumber() {
        CalculatorG.calculate("sqrt(-9)");
    }

    @Test
    public void testMemoryOperations() {
        CalculatorG.calculate("3 + 5 M+");
        assertEquals(8.0, CalculatorG.recallMemory(), 0.001);

        CalculatorG.storeInMemory(15.5);
        assertEquals(15.5, CalculatorG.recallMemory(), 0.001);

        CalculatorG.clearMemory();
        assertEquals("No values stored in memory.", CalculatorG.recallAllMemory());
    }

    @Test
    public void testRecallAllMemory() {
        CalculatorG.storeInMemory(8.0);
        CalculatorG.storeInMemory(15.5);
        CalculatorG.storeInMemory(42.0);
        CalculatorG.storeInMemory(3.14);
        CalculatorG.storeInMemory(99.99);
        CalculatorG.storeInMemory(27.5);

//        String expected = "Stored values: 27.5, 15.5, 42.0, 3.14, 99.99 ";
        String expected = "Stored values: 15.5, 42.0, 3.14, 99.99, 27.5";
        assertEquals(expected, CalculatorG.recallAllMemory());
    }
}
