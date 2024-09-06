package problems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import problems.calculator_entity_oops.launcher.CalculatorOld;

public class CalculatorOldTest {

    @Test
    public void testAddition() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("5 + 2");
        assertEquals("7.0", result);
    }

    @Test
    public void testSubtraction() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("5 - 2");
        assertEquals("3.0", result);
    }

    @Test
    public void testMultiplication() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("5 * 2");
        assertEquals("10.0", result);
    }

    @Test
    public void testDivision() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("10 / 2");
        assertEquals("5.0", result);
    }

    @Test
    public void testDivisionByZero() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("10 / 0");
        assertEquals("Error: Cannot divide by zero", result);
    }

    @Test
    public void testInvalidNumberFormat() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("abc + 2");
        assertEquals("Error: Invalid number format", result);
    }

    @Test
    public void testInvalidInputFormat() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("5 + ");
        assertEquals("Error: Invalid input format", result);
    }

    @Test
    public void testEmptyInput() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate("");
        assertEquals("Error: Input is empty or null", result);
    }

    @Test
    public void testNullInput() {
        CalculatorOld calculatorOld = new CalculatorOld();
        String result = calculatorOld.calculate(null);
        assertEquals("Error: Input is empty or null", result);
    }
}
