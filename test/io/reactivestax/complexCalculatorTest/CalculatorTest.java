//package complexCalculatorTest;
//
//import org.junit.Before;
//import org.junit.Test;
//import stringsAndArraysProblems.ComplexCalculator;
//
//import static org.junit.Assert.*;
//
//public class CalculatorTest {
//
//    @Before
//    public void setUp() {
//        ComplexCalculator.clearMemory();
//    }
//
//    @Test
//    public void testBasicOperations() {
//        assertEquals(5.6, ComplexCalculator.calculate("3.5 + 2.1"), 0.001);
//        assertEquals(6.0, ComplexCalculator.calculate("10 - 4"), 0.001);
//        assertEquals(42.0, ComplexCalculator.calculate("6 * 7"), 0.001);
//        assertEquals(4.0, ComplexCalculator.calculate("8 / 2"), 0.001);
//    }
//
//    @Test
//    public void testDivisionByZero() {
//        assertEquals(Double.NaN, ComplexCalculator.calculate("10 / 0"), 0.001);
//    }
//
//    @Test
//    public void testMultipleOperations() {
//        assertEquals(10.0, ComplexCalculator.calculate("3 + 5 * 2 - 4 / 2"), 0.001);
//    }
//
//    @Test
//    public void testParentheses() {
//        assertEquals(6.0, ComplexCalculator.calculate("3 + (2 * 4) - 5"), 0.001);
//        assertEquals(36.0, ComplexCalculator.calculate("(10 + 2) * 3"), 0.001);
//    }
//
//    @Test
//    public void testAdvancedOperations() {
//        assertEquals(4.0, ComplexCalculator.calculate("sqrt(16)"), 0.001);
//        assertEquals(8.0, ComplexCalculator.calculate("2 ^ 3"), 0.001);
//    }
//
//    @Test(expected = ArithmeticException.class)
//    public void testSquareRootOfNegativeNumber() {
//        ComplexCalculator.calculate("sqrt(-9)");
//    }
//
//    @Test
//    public void testMemoryOperations() {
//        ComplexCalculator.calculate("3 + 5 M+");
//        assertEquals(8.0, ComplexCalculator.recallMemory(), 0.001);
//
//        ComplexCalculator.storeInMemory(15.5);
//        assertEquals(15.5, ComplexCalculator.recallMemory(), 0.001);
//
//        ComplexCalculator.clearMemory();
//        assertEquals("No values stored in memory.", ComplexCalculator.recallAllMemory());
//    }
//
//    @Test
//    public void testRecallAllMemory() {
//        ComplexCalculator.storeInMemory(8.0);
//        ComplexCalculator.storeInMemory(15.5);
//        ComplexCalculator.storeInMemory(42.0);
//        ComplexCalculator.storeInMemory(3.14);
//        ComplexCalculator.storeInMemory(99.99);
//        ComplexCalculator.storeInMemory(27.5);
//
//        String expected = "Stored values: 27.5, 15.5, 42.0, 3.14, 99.99";
//        assertEquals(expected, ComplexCalculator.recallAllMemory());
//    }
//}