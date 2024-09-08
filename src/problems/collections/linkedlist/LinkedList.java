package problems.collections.linkedlist;

@SuppressWarnings("java:S106")
public class LinkedList<T> {
    Node headNode;
    int size = 0;

    public LinkedList() {
        headNode = null;
    }

    public void addLast(int data) {
        Node currentNode = new Node(data, null);
        if (headNode == null) {
            headNode = currentNode;
        } else {
            Node node = headNode;
            while (node.pointer != null) {
                node = node.pointer;
            }
            node.pointer = currentNode;
        }
        size++;
    }

    public void addFirst(int data) {
        Node tempNode = new Node(data, null);
        headNode = new Node(data, tempNode);
        size++;
    }

    public int removeFirst() {
        int value = 0;
//        for (int i = 0; i < myLinkedList.length - 1; i++) {
//            myLinkedList[i] = myLinkedList[i + 1];
//        }
        size--;
        return value;
    }

    public int removeLast() {
        int value = 0;
//        for (int i = 0; i < myLinkedList.length - 1; i++) {
//            myLinkedList[i] = myLinkedList[i + 1];
//        }
        size--;
        return value;
    }

    public void printList() {
        Node currentNode =  headNode;
        while (currentNode  != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.pointer;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size > 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        LinkedList<Integer> integerMyLinkedList = new LinkedList<>();
        integerMyLinkedList.addFirst(1);
        integerMyLinkedList.addLast(2);
        integerMyLinkedList.addLast(3);

//        integerMyLinkedList.removeFirst();

        integerMyLinkedList.printList();
    }
}
