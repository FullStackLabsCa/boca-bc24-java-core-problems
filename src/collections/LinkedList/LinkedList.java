package collections.LinkedList;

import java.util.NoSuchElementException;

public class LinkedList{
    Node head;
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addFirst(10);
        ll.addFirst(20);
        ll.addFirst(30);
        ll.addFirst(40);
        ll.printList();
    }

    int size=0;
    public void addLast(int value) {
        Node node = new Node(null, value);
        if(head == null){
            head = node;
        }else{
            if(size==1){
                head.next = node;
            }else{
                Node current = head;
                while(current.next!=null){
                    current = current.next;
                    System.out.println(current.value);
                }
                current.next = node;
            }

        }
        size++;
    }

    public int size() {
        return size;
    }

    public void addFirst(int value) {
        Node node = new Node(null, value);
        if(size==0){
            head = node;
            size++;
        }else{
        node.next = head;
        head = node;
          size++;
        }
    }

    public int removeFirst() {
        if(size==0){
            throw new NoSuchElementException();
        }
        int value = head.value;
        head = head.next;
        size--;
        return value;
    }

    public int removeLast() {
        int value;
        if(size==0){
            throw new NoSuchElementException();
        }else if(size == 1){
            value = head.value;
            head = null;
            return  value;
        }else{
            Node current = head;
            while(current.next.next!=null){
                current = current.next;
            }
            value = current.next.value;
            current.next = null;
            size--;
            return value;
        }

    }

    public boolean isEmpty() {
        if(size ==0){
            return true;
        }else{
            return false;
        }

    }

    public void printList() {
        if(size==0){
            throw new NullPointerException();
        }else{
            Node current = head;
            while(current.next!=null){
                System.out.print(current.value +"-->");
                current = current.next;
            }
            System.out.println(current.value);
        }

    }
}
