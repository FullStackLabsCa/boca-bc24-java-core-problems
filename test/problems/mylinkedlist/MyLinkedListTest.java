package problems.mylinkedlist;

import org.junit.Before;
import org.junit.Test;
import mylinkedlist.MyLinkedList;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    private MyLinkedList linkedList;

    @Before
    public void setUp() {
        linkedList = new MyLinkedList();
    }

    @Test
    public void testAddLast() {
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);

        assertEquals(3, linkedList.size());
    }

    @Test
    public void testAddFirst() {
        linkedList.addFirst(10);
        linkedList.addFirst(20);
        linkedList.addFirst(30);

    assertEquals(3, linkedList.size());
    }

    @Test
    public void testRemoveFirst() {
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);

        int removed = linkedList.removeFirst();
        assertEquals(10, removed);
        assertEquals(2, linkedList.size());
    }

    @Test
    public void testRemoveLast() {
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);

        int removed = linkedList.removeLast();
        assertEquals(30, removed);
        assertEquals(2, linkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(linkedList.isEmpty());
        linkedList.addLast(10);
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, linkedList.size());
        linkedList.addLast(10);
        assertEquals(1, linkedList.size());
    }

    @Test
    public void testPrintList() {
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);

        linkedList.printList();
    }
}
