package problems.collections.linkedlist;

import java.util.NoSuchElementException;

public class LinkedList {

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(int number) {
        if (head == null) {
            head = new Node(number);
        } else {
            Node current = head;
            head = new Node(number);
            head.next = current;
        }
        size++;
    }

    public void addLast(int number) {
        Node node = new Node(number);
        if (head == null) {
            head = node;
        } else if (size == 1) {
            head.next = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    public int removeFirst() {
        Node current = head;
        if (size == 0) throw new NoSuchElementException();
        else if (size == 1) head = null;
        else head = current.next;
        size--;

        return current.data;
    }

    public int removeLast() {
        Node current = head;
        if (size == 0) throw new NoSuchElementException();
        else if (size == 1) head = null;
        else {
            while (current.next.next != null) {
                current = current.next;
            }
            Node temp = current.next;
            current.next = null;
            current = temp;
        }
        size--;

        return current.data;
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        if (size == 0) throw new NullPointerException();
        else {
            Node current = head;
            while (current.next != null) {
                System.out.print(current.data + "->");
                current = current.next;
            }
            System.out.print(current.data);
        }
    }

}

