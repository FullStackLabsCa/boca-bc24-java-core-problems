package problems.linkedlist;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MyLinkedListTest {
    private MyLinkedList myLinkedList;

    @Before
    public void setUp() {
        myLinkedList = new MyLinkedList();
    }

    @Test
    public void testAddLast() {
        myLinkedList.addLast(10);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);

        assertEquals(3, myLinkedList.size());
    }

    @Test
    public void testAddFirst() {
        myLinkedList.addFirst(10);
        myLinkedList.addFirst(20);
        myLinkedList.addFirst(30);

        assertEquals(3, myLinkedList.size());
    }
    @Test
    public void testRemoveFirst() {
        myLinkedList.addLast(10);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);

        int removed = myLinkedList.removeFirst();
        assertEquals(10, removed);
        assertEquals(2, myLinkedList.size());
    }

    @Test
    public void testRemoveLast() {
        myLinkedList.addLast(10);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);

        int removed = myLinkedList.removeLast();
        assertEquals(30, removed);
        assertEquals(2, myLinkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(myLinkedList.isEmpty());
        myLinkedList.addLast(10);
        assertFalse(myLinkedList.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, myLinkedList.size());
        myLinkedList.addLast(10);
        assertEquals(1, myLinkedList.size());
    }

    @Test
    public void testPrintList() {
        myLinkedList.addLast(10);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);

        myLinkedList.printList();
    }
}
