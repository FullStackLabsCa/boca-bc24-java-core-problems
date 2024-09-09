package problems.collections.linkedlist;

public class MyLinkedList<E extends Integer> {
    private int size = 0;
    private Node<E> headNode;
    private Node<E> tailNode;

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "size=" + size +
                ", headNode=" + headNode +
                '}';
    }

    //empty constructor to store the value
//    public MyLinkedList() {
//    }

    public void addLast(E element) {
        Node<E> tempNode = new Node<>(element);
        if (size == 0) {
            addFirst(element);
        } else {
            Node<E> currentNode = headNode;
            while (currentNode.link != null) {
                currentNode = currentNode.link;
            }
            currentNode.link = tempNode;
            size++;
        }
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.link = headNode;
        headNode = newNode;
        size++;
    }


    public int removeFirst() {
        E removedData = headNode.data;
        if (size == 0) {
            throw new NullPointerException("Don't have first element. So, first and last value is NULL");
        } else {
            headNode = headNode.link;
            size--;
        }
        return removedData;
    }

    public Node get(int size) {
        Node node = headNode;
        for (int i = 1; i < size; i++) {
            node = node.link;
        }
        return node;
    }

    public int removeLast() {
        if (size == 1) {
            return removeFirst();
        }
        Node lastNode = get(size);
        Integer lastNodeData = lastNode.data;
        Node secondLast = get(size - 1);
        lastNode = secondLast;
        lastNode.link = null;
        size--;
        return lastNodeData;
    }


    public boolean isEmpty() {
        if (headNode == null) {
            return true;
        } else return false;
    }

    public void printList() {
        Node<E> tempNode = headNode;
        while (tempNode != null) {
            System.out.print(tempNode.data + " -> ");
            tempNode = tempNode.link;
        }
        System.out.println("END");
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> integerMyLinkedList = new MyLinkedList<>();

        integerMyLinkedList.addFirst(12);
        integerMyLinkedList.addFirst(13);
        integerMyLinkedList.addFirst(14);
        integerMyLinkedList.addFirst(15);
        integerMyLinkedList.addLast(20);
        System.out.println("=========================================================");
        System.out.println("integerMyLinkedList.size() = " + integerMyLinkedList.size());

        System.out.println("=========================================================");
        System.out.println("integerMyLinkedList.get(1) = " + integerMyLinkedList.get(1));
        System.out.println("integerMyLinkedList.get(2) = " + integerMyLinkedList.get(2));
        System.out.println("integerMyLinkedList.get(3) = " + integerMyLinkedList.get(3));
        System.out.println("integerMyLinkedList.get(4) = " + integerMyLinkedList.get(4));
        System.out.println("integerMyLinkedList.get(5) = " + integerMyLinkedList.get(5));
        System.out.println("=========================================================");
        integerMyLinkedList.printList();

        System.out.println("======================= Remove First =======================");
        integerMyLinkedList.printList();
        System.out.println("integerMyLinkedList.removeFirst() = " + integerMyLinkedList.removeFirst());
        integerMyLinkedList.printList();

        System.out.println("====================== Remove Last ========================");
        integerMyLinkedList.printList();
        System.out.println("integerMyLinkedList.removeLast() = " + integerMyLinkedList.removeLast());
        integerMyLinkedList.printList();

    }
}
