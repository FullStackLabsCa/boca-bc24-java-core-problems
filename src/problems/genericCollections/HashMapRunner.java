package problems.genericCollections;

public class HashMapRunner {
    public static void main(String[] args) {

        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(null,"Null value");
        hashMap.put(100,"Apple");
        hashMap.put(101,"Orange");
        hashMap.put(102,"Banana");
        hashMap.put(100,"Apples");
        hashMap.put(null,"Nvalue");

        System.out.println(hashMap);


        System.out.println(hashMap.get(100));
        System.out.println(hashMap.get(101));
        System.out.println(hashMap.get(102));
        System.out.println(hashMap.get(null));
    }
}
