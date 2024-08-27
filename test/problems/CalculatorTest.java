package problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("5 + 2");
        assertEquals("7.0", result);
    }

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("5 - 2");
        assertEquals("3.0", result);
    }

    @Test
    public void testMultiplication() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("5 * 2");
        assertEquals("10.0", result);
    }

    @Test
    public void testDivision() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("10 / 2");
        assertEquals("5.0", result);
    }

    @Test
    public void testDivisionByZero() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("10 / 0");
        assertEquals("Error: Cannot divide by zero", result);
    }

    @Test
    public void testInvalidNumberFormat() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("abc + 2");
        assertEquals("Error: Invalid number format", result);
    }

    @Test
    public void testInvalidInputFormat() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("5 + ");
        assertEquals("Error: Invalid input format", result);
    }

    @Test
    public void testEmptyInput() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate("");
        assertEquals("Error: Input is empty or null", result);
    }

    @Test
    public void testNullInput() {
        Calculator calculator = new Calculator();
        String result = calculator.calculate(null);
        assertEquals("Error: Input is empty or null", result);
    }
}
