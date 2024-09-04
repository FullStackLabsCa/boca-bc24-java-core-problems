package problems;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Before
    public void setUp() {
        Calculator.clearMemory();
    }

    @Test
    public void testBasicOperations() {
        assertEquals(5.6, Calculator.calculate("3.5 + 2.1"), 0.001);
        assertEquals(6.0, Calculator.calculate("10 - 4"), 0.001);
        assertEquals(42.0, Calculator.calculate("6 * 7"), 0.001);
        assertEquals(4.0, Calculator.calculate("8 / 2"), 0.001);
    }

    @Test
    public void testDivisionByZero() {
        assertEquals(Double.NaN, Calculator.calculate("10 / 0"), 0.001);
    }

    @Test
    public void testMultipleOperations() {
        assertEquals(11.0, Calculator.calculate("3 + 5 * 2 - 4 / 2"), 0.001);
    }

    @Test
    public void testParentheses() {
        assertEquals(6.0, Calculator.calculate("3 + (2 * 4) - 5"), 0.001);
        assertEquals(36.0, Calculator.calculate("(10 + 2) * 3"), 0.001);
    }

    @Test
    public void testAdvancedOperations() {
        assertEquals(4.0, Calculator.calculate("sqrt(16)"), 0.001);
        assertEquals(8.0, Calculator.calculate("2 ^ 3"), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testSquareRootOfNegativeNumber() {
        Calculator.calculate("sqrt(-9)");
    }

    @Test
    public void testMemoryOperations() {
        Calculator.calculate("3 + 5 M+");
        assertEquals(8.0, Calculator.recallMemory(), 0.001);

        Calculator.storeInMemory(15.5);
        assertEquals(15.5, Calculator.recallMemory(), 0.001);

        Calculator.clearMemory();
        assertEquals("No values stored in memory.", Calculator.recallAllMemory());
    }

    @Test
    public void testRecallAllMemory() {
        Calculator.storeInMemory(8.0);
        Calculator.storeInMemory(15.5);
        Calculator.storeInMemory(42.0);
        Calculator.storeInMemory(3.14);
        Calculator.storeInMemory(99.99);
        Calculator.storeInMemory(27.5);

        String expected = "Stored values: 27.5, 15.5, 42.0, 3.14, 99.99";
        assertEquals(expected, Calculator.recallAllMemory());
    }

//    @Test
//    public void testAddition() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("5 + 2");
//        assertEquals("7.0", result);
//    }
//
//    @Test
//    public void testSubtraction() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("5 - 2");
//        assertEquals("3.0", result);
//    }
//
//    @Test
//    public void testMultiplication() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("5 * 2");
//        assertEquals("10.0", result);
//    }
//
//    @Test
//    public void testDivision() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("10 / 2");
//        assertEquals("5.0", result);
//    }
//
//    @Test
//    public void testDivisionByZero() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("10 / 0");
//        assertEquals("Error: Cannot divide by zero", result);
//    }
//
//    @Test
//    public void testInvalidNumberFormat() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("abc + 2");
//        assertEquals("Error: Invalid number format", result);
//    }
//
//    @Test
//    public void testInvalidInputFormat() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("5 + ");
//        assertEquals("Error: Invalid input format", result);
//    }
//
//    @Test
//    public void testEmptyInput() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate("");
//        assertEquals("Error: Input is empty or null", result);
//    }
//
//    @Test
//    public void testNullInput() {
//        Calculator calculator = new Calculator();
//        String result = calculator.calculate(null);
//        assertEquals("Error: Input is empty or null", result);
//    }


}
