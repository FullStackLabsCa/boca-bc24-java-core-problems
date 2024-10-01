package mylinkedlist;

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

    public int removeFirst(){
        Node temp = head;
        head = head.getRefrence();
        size--;
        return temp.getData();
    }
    public int removeLast(){
        Node temp = head;
        //Node current = head;
        while(true){
            Node nextNode = temp.refrence;
            if(nextNode.refrence == null){
                temp.refrence = null;
                size --;
                return nextNode.getData();
            }
            else {
                temp = nextNode;
            }

        }
    }

    public boolean isEmpty(){
        return  size==0;
    }

    public int size(){
        return size;
    }
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getRefrence();
        }
        System.out.println();
    }
}
