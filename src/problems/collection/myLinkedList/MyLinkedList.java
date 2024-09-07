package problems.collection.myLinkedList;


import java.util.NoSuchElementException;

public class MyLinkedList {

    static class Node {
        int data;       // Data stored in the node
        Node next;      // Reference to the next node

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }


    private Node head; // Reference to the first node
    private Node tail; // Reference to the last node
    private int size; // Size of the list

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Add Last: to add an element at the last
    public void addLast(int data) {

        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Add First: to add an element at the first
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Remove First: to remove an element at the first
    public int removeFirst() {
        int removedData=0;
        if (head == null) {
            throw new NoSuchElementException("List is Empty");
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            removedData = head.data;
            head = head.next;
        }
        size--;
        System.out.println("Remove First Value: "+removedData);
        return removedData;
    }

    // Remove Last: to remove an element at the last
    public int removeLast() {

        int removedData = 0;

        if (head == null) {
            throw new NoSuchElementException("List is Empty");
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            removedData = tail.data;
            current.next = null;
            tail = current;
        }
        size--;
        System.out.println("Remove Last Value: "+removedData);
        return removedData;
    }

    // Print all elements
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Empty list check
    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }


    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        System.out.print("After adding and making new list: ");
        list.printList();
        System.out.println("Size: "+ list.size);

        list.addFirst(989);
        System.out.print("Add first: " );
        list.printList();
        System.out.println("Size: "+ list.size);

        list.addLast(79);
        System.out.print("Add last: " );
        list.printList();
        System.out.println("Size: "+ list.size);

        System.out.println("------------------");

        list.removeFirst();
        list.printList();
        System.out.println("Size: "+ list.size);

        list.removeLast();
        list.printList();
        System.out.println("Size: "+ list.size);
    }


}
