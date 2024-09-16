package collections;

import java.util.Arrays;
import java.util.LinkedList;

public class MyLinkedList {
    Node head = null;
    int index = 0;

    public void addFirst(int element) {
        Node node = new Node(element);
        if(head == null){
            head = node;
            index++;
        }else {
            node.next = head;
            head = node;
            index++;
        }
        index++;
    }

    public void addLast(int element) {
        Node node = new Node(element);
        if (head == null){
            head = node;
            index++;
        } else {
            Node current = head;
            if (current.next != null) {
                current = current.next;
            }
            current.next = node;
            index++;
        }
    }



    public int removeFirst() {
            return 0;
    }

    public int removeLast() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }
    public void resize(){

    }

    public int size() {
        return index;
    }

    public void printList() {
        Node current = head;
        while (current.element != 0){
            System.out.println(current.element);
            if(current.next != null){
                current = current.next;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addFirst(10);
    myLinkedList.addFirst(20);
    myLinkedList.addFirst(30);
    myLinkedList.addFirst(40);
    myLinkedList.addLast(5);
//    myLinkedList.addLast(6);
//    myLinkedList.addLast(7);


    myLinkedList.printList();

//        System.out.println(myLinkedList.toString());
    }
}
