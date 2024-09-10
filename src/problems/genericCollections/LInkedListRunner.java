package problems.genericCollections;


public class LInkedListRunner {

    public static void main(String[] args) {


        LinkedList linkedList = new LinkedList();

        linkedList.addFirst(10);
        linkedList.addFirst(20);
        linkedList.addFirst(30);
        linkedList.addFirst(40);

        System.out.println(linkedList);

        linkedList.addLast(50);
        System.out.println(linkedList);

        linkedList.addLast(60);
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.removeLast();
//        linkedList.removeLast();
//        linkedList.removeLast();
//        linkedList.removeLast();
//        linkedList.removeLast();
        System.out.println(linkedList);
        System.out.println(linkedList.size());


       /* linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        System.out.println(linkedList);

        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        System.out.println(linkedList);

        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        System.out.println(linkedList);

        int removed = linkedList.removeFirst();
        System.out.println("removed : "+removed);
        System.out.println("size : "+linkedList.size());
        System.out.println("isEmpty : "+linkedList.isEmpty());
        System.out.println("size : "+linkedList.size());
*/
    }
}
