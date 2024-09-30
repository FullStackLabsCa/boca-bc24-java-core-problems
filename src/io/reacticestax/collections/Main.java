package io.reacticestax.collections;

public class Main {
    public static void main(String[] args) {

        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        list.add(19);
        list.add(20);
        list.remove(3);
        System.out.println("size of list : " + list.size());
        list.clear();

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\n");
        }
        System.out.print("\n" + list + " " + " \n");

        System.out.println(list.size());
    }
}

