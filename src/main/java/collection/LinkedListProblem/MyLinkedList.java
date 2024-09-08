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
            head=newNode;
            size++;
        }
        else {
            Node<T> currentNode = head;
            while(currentNode.next != null) {
                currentNode = currentNode.next;
            }
           currentNode.next=newNode;
            size++;

        }
    }
//
//    public int removeFirst() {
//        Node<T> newNode = new Node<>();
//    }

    public int size() {
        return size;
    }
//
//    public int removeFirst() {
//    }
//
//    public int removeLast() {
//    }
//
//    public boolean isEmpty() {
//        if()
//    }
//
    public void printList() {
        Node<T> currentNode = head;
        if(head==null){
            System.out.println("Nothing to print");
            return;
        }
      while (currentNode!=null){
          System.out.println(currentNode.data+"  ");
          currentNode =currentNode.next;
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
    newnode.printList();

}



}
