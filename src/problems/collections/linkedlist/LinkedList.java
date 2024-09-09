package problems.collections.linkedlist;

import java.util.NoSuchElementException;

@SuppressWarnings("java:S106")
public class LinkedList<T> {
    Node headNode;
    int size = 0;

    public LinkedList() {
        headNode = null;
    }

    public void addLast(int data) {
        Node currentNode = new Node(data, null);
        if (headNode == null) {
            headNode = currentNode;
        } else {
            Node node = headNode;
            while (node.pointer != null) {
                node = node.pointer;
            }
            node.pointer = currentNode;
        }
        size++;
    }

    public void addFirst(int data) {
        if (headNode == null) {
            headNode = new Node(data, null);
        } else {
            Node currentNode = new Node(data, headNode);
            headNode = currentNode;
        }
        size++;
    }

    public int removeFirst() {
        if (size > 0) {
            Node currentNode = headNode;
            headNode = currentNode.pointer;
            size--;
            return currentNode.data;
        }
        throw new NoSuchElementException();
    }

    public int removeLast() {
        int value = 0;
        if (size > 0) {
            Node currentNode = headNode;
            Node previousNode = currentNode;
            while (currentNode.pointer != null) {
                previousNode = currentNode;
                currentNode = currentNode.pointer;
            }
            if (previousNode.pointer == null) {
                headNode = null;
            }
            previousNode.pointer = null;
            size--;
            value = currentNode.data;
        }
        return value;
//        throw new NoSuchElementException();
    }

    public void printList() {
        Node currentNode =  headNode;
        while (currentNode  != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.pointer;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size > 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        LinkedList<Integer> integerMyLinkedList = new LinkedList<>();
        integerMyLinkedList.addFirst(1);
        integerMyLinkedList.addLast(2);
        integerMyLinkedList.addLast(3);

//        integerMyLinkedList.removeFirst();

        integerMyLinkedList.printList();
    }
}
