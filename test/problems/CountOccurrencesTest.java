package problems;

import org.junit.Test;
import static org.junit.Assert.*;
import problems.generic.CountOccurrences;

public class CountOccurrencesTest {

    @Test
    public void testCountOccurrencesWithIntegers() {
        Integer[] intArray = {1, 2, 3, 2, 4, 2, 5};
        assertEquals(3, CountOccurrences.countOccurrences(intArray, 2));
        assertEquals(0, CountOccurrences.countOccurrences(intArray, 6));
    }

    @Test
    public void testCountOccurrencesWithStrings() {
        String[] strArray = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        assertEquals(3, CountOccurrences.countOccurrences(strArray, "apple"));
        assertEquals(0, CountOccurrences.countOccurrences(strArray, "orange"));
    }

    @Test
    public void testCountOccurrencesWithDoubles() {
        Double[] dblArray = {1.1, 2.2, 3.3, 2.2, 4.4, 2.2, 5.5};
        assertEquals(3, CountOccurrences.countOccurrences(dblArray, 2.2));
        assertEquals(0, CountOccurrences.countOccurrences(dblArray, 6.6));
    }

    @Test
    public void testCountOccurrencesWithCharacters() {
        Character[] charArray = {'a', 'b', 'a', 'c', 'b', 'a'};
        assertEquals(3, CountOccurrences.countOccurrences(charArray, 'a'));
        assertEquals(0, CountOccurrences.countOccurrences(charArray, 'z'));
    }

    @Test
    public void testCountOccurrencesWithEmptyArray() {
        Integer[] emptyArray = {};
        assertEquals(0, CountOccurrences.countOccurrences(emptyArray, 2));
    }

    @Test
    public void testCountOccurrencesWithNullElement() {
        String[] arrayWithNull = {"a", null, "b", null, "c"};
        assertEquals(2, CountOccurrences.countOccurrences(arrayWithNull, null));
        assertEquals(0, CountOccurrences.countOccurrences(arrayWithNull, "d"));
    }

    @Test
    public void testCountOccurrencesWithSingleElementArray() {
        Integer[] singleElementArray = {1};
        assertEquals(1, CountOccurrences.countOccurrences(singleElementArray, 1));
        assertEquals(0, CountOccurrences.countOccurrences(singleElementArray, 2));
    }
}