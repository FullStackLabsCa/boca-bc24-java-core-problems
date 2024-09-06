package problems.collection.linkedList;

import java.util.ArrayList;
import java.util.LinkedList;

public class PracticeLinkedList {

    public static void main(String[] args) {


        ArrayList arrayList = new ArrayList();
        arrayList.add("Anil");
        arrayList.add("Anil");
        arrayList.add("Anil");
        arrayList.add("Anil");

        arrayList.get(1);


        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.get(1);
        System.out.println("First List: " + list);

        list.addFirst(99);
        System.out.println("Add First: " + list);

        list.addLast(00);
        System.out.println("Add Last: " + list);

        list.removeFirst();
        System.out.println("Remove First: " + list);

        list.removeLast();
        System.out.println("Remove Last: " + list);


    }
}
