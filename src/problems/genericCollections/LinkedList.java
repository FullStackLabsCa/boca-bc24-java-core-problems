package problems.genericCollections;


class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {

    }

    public void addLast(int i) {
        Node newNode = new Node(i);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void addFirst(int i) {
        Node newNode = new Node(i);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public int removeFirst() {
        if(head == null){
            System.out.println("List empty!");
            throw new NullPointerException();
        }
        int removedData = head.data;
        head = head.next;
        size--;
        return removedData;
    }

    public int removeLast() {
        int removedData;
        Node current = head;
        Node previous =null;
        if(head == null){
            System.out.println("List empty!");
            throw new NullPointerException();
        }
        if (head.next == null) {
            removedData = head.data;
            head = null;
            size--;
            return removedData;
        }
        while(current.next !=null){
            previous = current;
            current = current.next;
        }
        removedData =current.data;
        previous.next = null;
        size--;
        return removedData;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        result.append("[");
        while (current != null) {
            result.append(current.data);
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println(result);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        result.append("[");
        while (current != null) {
            result.append(current.data);
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
