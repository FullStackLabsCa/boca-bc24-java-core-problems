package collection_Implementation_using_array;

import java.util.Arrays;
import java.util.List;

class Node<T extends Integer> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList<T extends Integer> {
    Node<T> head;
    int size;

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }


    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            size++;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            size++;
        }
    }

    public T removeFirst() {
        T removeElement = null;
        if (head == null) {
            System.out.println("List is empty");
        } else {
            removeElement = head.data;
            head = head.next;
            size--;
        }
        return removeElement;
    }

    public T removeLast() {
        T removeElement = null;
        if (head == null) {
            System.out.println("List is empty");
            throw new NullPointerException();
        } else {
            Node<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            removeElement = current.next.data;
            current.next = null;
            size--;
            return removeElement;
        }
    }

    public void printList() {
        if (head == null) {
            throw new NullPointerException();
        } else {
            Node current = head;
            while (current.data != null) {
                System.out.println(current.data + " ");
                if (current.next!=null) {
                    current = current.next;
                }else break;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else return false;
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(70);
        list.printList();
    }
}