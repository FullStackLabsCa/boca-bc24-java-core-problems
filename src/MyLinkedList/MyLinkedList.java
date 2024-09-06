package MyLinkedList;

public class MyLinkedList {
    int size = 0;
   private Node head;
   private Node tail;

    public MyLinkedList() {
        this.size = size;
        this.head = head;
        this.tail = tail;
    }

    public void addLast(int valueOfElement) {
        Node newNode = new Node(valueOfElement);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setRefrence(newNode);
            tail = newNode;
        }
        size++;
    }

    public void addFirst(int valueOfElement){
        Node newNode = new Node(valueOfElement);
        newNode.setRefrence(head);
        head = newNode;
        size++;
    }
    public int size(){
        return size;
    }
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + " => "+current.getRefrence());
            current = current.getRefrence();
        }
        System.out.println();
    }
}
