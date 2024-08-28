package problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import generic.OddCounter;
import static org.junit.Assert.assertEquals;

/*
 * Test for the following problem
 * Design and implement a generic method that can count the number of elements in a collection that satisfy
 * a specific property. In this specific case, the method should count the number of odd integers
 * in a collection of integers.
 *
 */
public class OddCounterTest {

    @Test
    public void testCountOddNumbersNumbersInList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int result = OddCounter.countOddNumbers(numbers);
        assertEquals(5, result);  // There are 5 odd numbers: 1, 3, 5, 7, 9
    }

    @Test
    public void testCountOddNumbersNumbersInSet() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(10, 15, 20, 25, 30, 35));
        int result = OddCounter.countOddNumbers(numbers);
        assertEquals(3, result);  // There are 3 odd numbers: 15, 25, 35
    }

    @Test
    public void testCountOddNumbersNumbersInEmptyCollection() {
        List<Integer> emptyList = Collections.emptyList();
        int result = OddCounter.countOddNumbers(emptyList);
        assertEquals(0, result);
    }

    @Test
    public void testCountOddNumbersWithNoOddNumbersNumbers() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        int result = OddCounter.countOddNumbers(numbers);
        assertEquals(0, result);  // There are no odd numbers
    }

    @Test
    public void testCountOddNumbersNumbersWithNullValues() {
        List<Integer> numbers = Arrays.asList(1, 3, null, 4, 7, null, 8);
        int result = OddCounter.countOddNumbers(numbers);
        assertEquals(3, result);  // There are 3 odd numbers: 1, 3, 7
    }
}