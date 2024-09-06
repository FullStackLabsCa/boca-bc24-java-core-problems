package problems.linkedlist;

import javax.management.OperationsException;

public class LinkedList<T> {
    Node<T> head;

    //to insert at the end
    public void addLast(T t) {
        if (head == null) {
            head = new Node<>(t);
        } else {
            Node<T> pointer = head;
            while (true) {
                if (pointer.next != null) {
                    pointer = pointer.next;
                } else {
                    pointer.next = new Node<>(t);
                    break;
                }
            }
        }
    }

    // to add at first
    public void addFirst(T t) {
        if (head == null) {
            head = new Node<>(t);
        } else {
            Node<T> temp = new Node<>(t);
            temp.next = head;
            head = temp;
        }
    }


    // to remove from the head / first
    public T removeFirst() {
        if (head == null) {
            System.out.println("There is no element in the linked list to remove");
            return null;
        } else {
            Node<T> pointer = head;
            head = head.next;
            return pointer.data;
        }
    }

    // to remove from the tail / last
    public T removeLast() {
        if (head == null) {
            System.out.println("There is no element in the linked list to remove");
            return null;
        } else {
            int counter = 0;
            Node<T> pointer = head;
            Node<T> nodeBeforePointer = null;
            while (true) {
                counter++;
                if (pointer.next != null) {
                    nodeBeforePointer = pointer;
                    pointer = pointer.next;
                } else {
                    break;
                }
            }
            nodeBeforePointer.next = null;
            return pointer.data;
        }
    }


    //To find whether the linked list is empty
    public boolean isEmpty() {
        return head == null;
    }


    public int size() {
        if (head == null) {
            System.out.println("Linked list does not have any nodes");
            return 0;
        }
        int counter = 0;
        Node<T> pointer = head;
        while (true) {
            counter++;
            if (pointer.next != null) {
                pointer = pointer.next;
            } else {
                break;
            }
        }
        return counter;
    }

    // to print tail / last node value
    public void printLast() {
        if (head == null) {
            System.out.println("There is no element in the linked list to remove");
        } else {
            int counter = 0;
            Node<T> pointer = head;
            while (true) {
                counter++;
                if (pointer.next != null) {
                    pointer = pointer.next;
                } else {
                    break;
                }
            }
            System.out.println("Data at the last node is = " + pointer.data);
        }
    }

    //to display all node value in the linked list
    public void printList() {
        System.out.println("========Display of all notes in Linked list ========");
        if (head == null) {
            System.out.println("Linked List does not have any node");
        } else {
            Node<T> pointer = head;
            while (true) {
                System.out.println(pointer.data);
                if (pointer.next != null) {
                    pointer = pointer.next;
                } else {
                    break;
                }
            }

        }
    }
}
