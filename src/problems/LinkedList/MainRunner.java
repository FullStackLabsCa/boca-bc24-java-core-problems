package problems.LinkedList;

public class MainRunner {
    public static void main(String[] args) {
        MyLinkedList myLinkedList= new MyLinkedList();

        myLinkedList.addLast(10);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);

        System.out.println(myLinkedList.toString());
    }
}
