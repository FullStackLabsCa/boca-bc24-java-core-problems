package problems.LinkedList;

public class MyLinkedList {
    Node head;
    Node tail;
    int size= 0;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyLinkedList@").append(Integer.toHexString(System.identityHashCode(this)))
                .append(" { ");

        Node current = head;
        while (current != null) {
            sb.append(current.toString()).append(" -> ");
            current = current.next;
        }

        sb.append("null }");
        return sb.toString();
    }

    public void addFirst(int i) {
        size++;
        Node node= new Node(i);

        if(head==null){
            head=node;
            tail=node;
        }
        else {
            head.next = node;
            head = node;
        }
    }

    public int removeFirst() {
       Node temp = head;
       head = head.next;
       size--;
       return temp.data;
    }

    public void addLast(int i){
        size++;
        Node node= new Node(i);

        if(head==null){
            head=node;
            tail=node;
        }
        else {
            tail.next=node;
            tail= node;
        }
    }

    public int removeLast() {
        Node temp  = head;
        while(true) {
            Node nextNode = temp.next;
            if (nextNode.next == null) {
                temp.next = null;
                size--;
                return nextNode.getData();
            }else{
                temp = nextNode;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
    }
}
