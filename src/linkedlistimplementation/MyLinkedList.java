package linkedlistimplementation;

public class MyLinkedList {
    private int size = 0;
    private Node head;
    private Node tail;

    public MyLinkedList() {
        this.head = null;
        this.tail =null;
    }


    public void addLast(int i) {
        Node newNode = new Node(i);
        size++;
        System.out.println(newNode.hashCode());
        if(head==null){
            head = newNode;
            tail = newNode;
        }else {
          tail.next = newNode;
          tail = newNode;
        }

    }
    public void addFirst(int i) {
        Node newNode = new Node(i);
        newNode.setNext(head);
        size++;
        head =newNode;
    }
    public int removeFirst() {
        Node temp = head;
        head = head.getNext();
        size--;
        return temp.getData();

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


    public void printList() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        if(current == null){
            sb.append(current);
        }
        while (current != null) {
            sb.append("{")
                    .append(current.data)
                    .append(", ")
                    .append(current.next != null ? current.next.hashCode() : "null")
                    .append("}");
            if (current.next != null) {
                sb.append(" -> ");
            }

            current = current.next;
        }
        System.out.println(sb);

    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

}
