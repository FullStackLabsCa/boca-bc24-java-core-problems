package problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import generics.ListToArray;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class ListToArrayTest {

    @Test
    public void testListToArrayWithIntegers() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        Integer[] expectedArray = {1, 2, 3, 4, 5};
        Integer[] resultArray = ListToArray.listToArray(intList, new Integer[intList.size()]);
        assertArrayEquals(expectedArray, resultArray);
    }

    @Test
    public void testListToArrayWithStrings() {
        List<String> strList = Arrays.asList("apple", "banana", "cherry");
        String[] expectedArray = {"apple", "banana", "cherry"};
        String[] resultArray = ListToArray.listToArray(strList, new String[strList.size()]);
        assertArrayEquals(expectedArray, resultArray);
    }

    @Test
    public void testListToArrayWithEmptyList() {
        List<String> emptyList = Arrays.asList();
        assertNull(ListToArray.listToArray(emptyList, new String[emptyList.size()]));
    }

    @Test
    public void testListToArrayWithNullList() {
        List<String> nullList = null;
        assertNull(ListToArray.listToArray(nullList,new String[0]));
    }

    @Test
    public void testListToArrayWithMixedElements() {
        List<Object> mixedList = Arrays.asList(1, "banana", 3.14);
        Object[] expectedArray = {1, "banana", 3.14};
        Object[] resultArray = ListToArray.listToArray(mixedList,new Object[mixedList.size()]);
        assertArrayEquals(expectedArray, resultArray);
    }
}