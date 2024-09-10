package problems.linkedlisttest;

public class MyLinkedList {
    int sizeCount = 0;


    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public Node head = null;
    public Node tail = null;

    public void addLast(int i) {
        Node newNode = new Node(i);

        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        sizeCount++;
        System.out.println("Element added to the last"+i);
        System.out.println(sizeCount);
        System.out.println();
    }

    public void addFirst(int i) {
        Node newNode = new Node(i);

        if(head == null) {
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
        sizeCount++;
        System.out.println("Element added to the first "+i);
        System.out.println(sizeCount);
        System.out.println();


    }

    public int removeFirst() {
        if (head == null) {
            System.out.println("List is empty");
            return -1;
        }
        int removedData = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        sizeCount--;
        System.out.println("Element removed from the first: " + removedData);
        System.out.println(sizeCount);
        System.out.println();
        return removedData;
    }

    public int removeLast() {
        if (head == null) {
            System.out.println("List is empty");
            return -1;
        }

        if (head == tail) {
            int removedData = tail.data;
            head = tail = null;
            sizeCount--;
            System.out.println("Element removed from the last: " + removedData);
            return removedData;
        }

        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }

        int removedData = tail.data;
        tail = current;
        tail.next = null;
        sizeCount--;

        System.out.println("Element removed from the last: " + removedData);
        return removedData;
    }

    public boolean isEmpty() {
        return head == null;
    }


    public void printList() {
        Node current = head;
        if(head == null){
            System.out.println("List is empty");
        }
        while (current!=null){
            System.out.println(current.data+" ");
            current = current.next;
        }
        System.out.println();

    }
    public int size() {
        return sizeCount;
    }


    private void add(int i) {

    }
    public static void main(String[] args) {

        MyLinkedList sList = new MyLinkedList();
            sList.addLast(10);
            sList.addLast(20);
            sList.addLast(30);
            sList.addLast(40);
            sList.addLast(50);

            sList.addFirst(1);
            sList.addFirst(2);
            sList.addFirst(3);
            sList.addFirst(4);

            sList.removeFirst();

            sList.removeLast();


            sList.printList();

            System.out.println("Size of the Singly LinkedList is : "+sList.size());


//        sList.addLast(0);
//        sList.addLast(10);
//        sList.addLast(20);
        }
}
