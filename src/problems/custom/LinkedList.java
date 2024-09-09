package problems.custom;

import java.util.*;

public class LinkedList {
    int size;
    Node head;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(int element) {
        if (head == null) {
            head = new Node(element);
            size++;
        } else {
            Node current = head;
            head = new Node(element);
            head.pointer = current;
            size++;
        }
    }


    public void addLast(int element) {
        Node node = new Node(element);
        if (head == null) {
            head = node;
        }
        if (size == 1) {
            head.pointer = node;
        } else {
            //looping and adding the reference to the node.Here we assign the head to current variable
            Node current = head;
            while (current.pointer != null) {
                current = current.pointer; //we receive the second last element that is not null
            }
            current.pointer = node;
        }
        size++;
    }

    public int size() {
        return size;
    }


    public int removeFirst() {
        //while removing the first element it is the head holding the data itself.

        if (size == 0) throw new NoSuchElementException();
        Integer data = head.data;
        head = head.pointer;
        size--;
        return data;
    }


    public int removeLast() {

        if (size == 0) throw new NoSuchElementException();
        Node current = head;
        //searching for the last element to be deleted where we can check the pointer which is null since the last element doesnt point to any other element in the linkedList.

        while (current.pointer != null) {
            current = current.pointer;
        }
        size--;
        return current.data;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void printList() {
        Node current =head;
        while (current!= null) {
            System.out.println(current.data + "--> ");
            current = current.pointer;
        }

    }
}
