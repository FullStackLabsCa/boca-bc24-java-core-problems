package collection.LinkedListProblem;

public class MyLinkedList<T> {
    Node<T> head;
    int size;

    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            size++;
        } else {
            Node<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            size++;

        }
    }

    public int size() {
        return size;
    }

    public Node<T> removeFirst() {
        if (head == null) {
            System.out.println("nothing to remove :");
        }
        Node<T> newNode = head;
        head = newNode.next;
        size--;
        return newNode;
    }

    public Node<T> removeLast() {
        Node<T> lastNode = head;
        if (head == null) {
            System.out.println("No Node available to display");

        }
        if (head.next == null) {
            Node<T> removeNode = head;
            head = null;
            size--;
            return removeNode;
        }
        Node<T> currentNode = head;
        Node<T> removedNode = currentNode.next;
        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = null;
        size--;
        return removedNode;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else return false;
    }

    //
    public void printList() {
        Node<T> currentNode = head;
        if (head == null) {
            System.out.println("Nothing to print");
            return;
        }
        while (currentNode != null) {
            System.out.println(currentNode.data + "  ");
            currentNode = currentNode.next;
        }

    }

    public static void main(String[] args) {

        MyLinkedList<Integer> newnode = new MyLinkedList<>();
        newnode.addFirst(1);
        newnode.addFirst(2);
        newnode.addLast(70);
        newnode.addLast(20);
        newnode.addLast(50);
        newnode.addLast(40);
        newnode.isEmpty();
        newnode.removeFirst();
        newnode.removeLast();
        newnode.printList();

    }


}
