import org.junit.Before;
import org.junit.Test;
import problems.array.MyArrayList;

import static org.junit.Assert.*;

public class MyArrayListTest {
    private MyArrayList<String> list;

    @Before
    public void setUp() {
        // Initialize the list before each test
        list = new MyArrayList<>();
    }

    // Test adding elements to the list
    @Test
    public void testAddElements() {
        list.add("Apple");
        list.add("Banana");

        assertEquals("Size should be 2 after adding two elements", 2, list.size());
        assertEquals("First element should be 'Apple'", "Apple", list.get(0));
        assertEquals("Second element should be 'Banana'", "Banana", list.get(1));
    }

    // Test adding elements beyond initial capacity
    @Test
    public void testAddBeyondInitialCapacity() {
        // Add elements more than default capacity (assuming default capacity is 10)
        for (int i = 1; i <= 12; i++) {
            list.add("Element" + i);
        }

        assertEquals("Size should be 12 after adding 12 elements", 12, list.size());
        assertEquals("Last element should be 'Element12'", "Element12", list.get(11));
    }

    // Test getting elements at valid indices
    @Test
    public void testGetElements() {
        list.add("Apple");
        list.add("Banana");

        assertEquals("Element at index 0 should be 'Apple'", "Apple", list.get(0));
        assertEquals("Element at index 1 should be 'Banana'", "Banana", list.get(1));
    }

    // Test getting element at invalid index (negative index)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementAtNegativeIndex() {
        list.add("Apple");
        list.get(-1); // This should throw IndexOutOfBoundsException
    }

    // Test getting element at invalid index (out of bounds)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementAtOutOfBoundsIndex() {
        list.add("Apple");
        list.get(2); // This should throw IndexOutOfBoundsException
    }

    // Test removing elements
    @Test
    public void testRemoveElements() {
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        String removedElement = list.remove(1);
        assertEquals("Removed element should be 'Banana'", "Banana", removedElement);
        assertEquals("Size should be 2 after removal", 2, list.size());
        assertEquals("Element at index 1 should now be 'Cherry'", "Cherry", list.get(1));
    }

    // Test removing element at first index
    @Test
    public void testRemoveFirstElement() {
        list.add("Apple");
        list.add("Banana");

        String removedElement = list.remove(0);
        assertEquals("Removed element should be 'Apple'", "Apple", removedElement);
        assertEquals("Size should be 1 after removal", 1, list.size());
        assertEquals("Element at index 0 should now be 'Banana'", "Banana", list.get(0));
    }

    // Test removing element at invalid index (negative index)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveElementAtNegativeIndex() {
        list.add("Apple");
        list.remove(-1); // This should throw IndexOutOfBoundsException
    }

    // Test removing element at invalid index (out of bounds)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveElementAtOutOfBoundsIndex() {
        list.add("Apple");
        list.remove(2); // This should throw IndexOutOfBoundsException
    }

    // Test list size
    @Test
    public void testListSize() {
        assertEquals("Size should be 0 for a new empty list", 0, list.size());

        list.add("Apple");
        list.add("Banana");

        assertEquals("Size should be 2 after adding two elements", 2, list.size());

        list.remove(0);
        assertEquals("Size should be 1 after removing one element", 1, list.size());
    }

    // Test clearing the list
    @Test
    public void testClearList() {
        list.add("Apple");
        list.add("Banana");

        list.clear();
        assertEquals("Size should be 0 after clearing the list", 0, list.size());
        assertTrue("List should be empty after clear", list.isEmpty());
    }

    // Test if the list is empty
    @Test
    public void testIsEmpty() {
        assertTrue("List should be empty for a new list", list.isEmpty());

        list.add("Apple");
        assertFalse("List should not be empty after adding an element", list.isEmpty());

        list.clear();
        assertTrue("List should be empty after clearing", list.isEmpty());
    }

    // Test adding null values
    @Test
    public void testAddNullValue() {
        list.add(null);
        assertNull("First element should be null", list.get(0));
        assertEquals("Size should be 1 after adding a null value", 1, list.size());
    }
}
