package linkedlist;

import java.util.NoSuchElementException;

public class MyLinkedList {

    private Node head, current;
    private int count = 0;

    public MyLinkedList() {
        this.head = null;
        this.count = 0;
    }

    public void addFirst(int data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            head = new Node(data, head);
        }
        count++;
    }

    public void addLast(int data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(data, null);
        }
        count++;
    }

    public int removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        int removedElement = 0;
        removedElement = head.data;
        head = head.next;
        count--;
        return removedElement;
    }

    public int removeLast() {
        int removedElement = 0;
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.next == null) {
            removedElement = head.data;
            head = null;
        } else {
            current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            removedElement = current.next.data;
            current.next = null;
            count--;
        }
        return removedElement;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("No nodes found in a linked list.");
        } else {
            current = head;
            while (current.next != null) {
                System.out.println("node 1 = " + current.data);
                current = current.next;
            }
            System.out.println("last node==" + current.data);
        }
    }
}
