package linkedlistimplementation;

import java.util.LinkedList;

public class MainRunner {
    public static void main(String[] args) {

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(10);
        myLinkedList.addFirst(5);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);
        myLinkedList.removeLast();
        myLinkedList.printList();
    }
}
