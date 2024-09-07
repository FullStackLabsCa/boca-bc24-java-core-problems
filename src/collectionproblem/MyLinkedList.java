package collectionproblem;
import java.util.NoSuchElementException;

public class MyLinkedList {

    private Node head;//first node reference
    private int size; //list size

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    //add the element to the end od the list
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; //if list is empty add new node is head
        } else {
            Node currentNode = head;
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.nextNode = head;
        head = newNode;
        size++;
    }

    public int removeFirst() {

        if (head == null) {
            throw new NoSuchElementException("No Node available to remove ");
        }
        Node intialHead = head;
        head = head.nextNode;
        size--;
        return intialHead.data;
    }


    public int removeLast() {
        Node lastNode = head;
        if (head == null) {
            throw new NoSuchElementException("No node available");
        } else if (size == 1) {
            head = null;
        } else {
            while (lastNode.nextNode.nextNode != null) {
                lastNode = lastNode.nextNode;
            }
            Node removeNode = lastNode.nextNode;
            lastNode.nextNode = null;
            lastNode = removeNode;
        }
        size--;
        return lastNode.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.nextNode;
        }
        System.out.println();
    }

}
