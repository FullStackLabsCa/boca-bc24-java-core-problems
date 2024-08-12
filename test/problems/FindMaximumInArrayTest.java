package problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMaximumInArrayTest {
    @Test
    public void testFindMaxWithPositiveNumbers() {
        int[] numbers = {1, 3, 7, 0, 5, 9};
        assertEquals(9, FindMaximumInArray.findMax(numbers));
    }

    @Test
    public void testFindMaxWithNegativeNumbers() {
        int[] numbers = {-1, -3, -7, -2, -5, -9};
        assertEquals(-1, FindMaximumInArray.findMax(numbers));
    }

    @Test
    public void testFindMaxWithMixedNumbers() {
        int[] numbers = {-1, 3, -7, 0, 5, 9};
        assertEquals(9, FindMaximumInArray.findMax(numbers));
    }

    @Test
    public void testFindMaxWithSingleElement() {
        int[] numbers = {42};
        assertEquals(42, FindMaximumInArray.findMax(numbers));
    }

    @Test
    public void testFindMaxWithEmptyArray() {
        int[] numbers = {};
        assertEquals(Integer.MIN_VALUE, FindMaximumInArray.findMax(numbers));
    }

    @Test
    public void testFindMaxWithNullArray() {
        int[] numbers = null;
        assertEquals(Integer.MIN_VALUE, FindMaximumInArray.findMax(numbers));
    }
}
