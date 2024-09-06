package problems.linkedlist;

public class LinkedListRunner {
    public static void main(String[] args) {
        LinkedList<Integer> myLinkedList = new LinkedList<>();

        System.out.println("Is empty: " + myLinkedList.isEmpty());
        myLinkedList.printList();
        System.out.println(myLinkedList.size());

        myLinkedList.addFirst(4);

        myLinkedList.printList();
        System.out.println("Size of the linked list:" + myLinkedList.size());

        myLinkedList.addLast(5);
        myLinkedList.addFirst(6);

//        myLinkedList.insert(-7);

        myLinkedList.printList();
        System.out.println("Size of the linked list:" + myLinkedList.size());

        System.out.println("Is empty: " + myLinkedList.isEmpty());
    }
}
