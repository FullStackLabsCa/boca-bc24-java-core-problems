package collection.hashmap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap();
        myHashMap.put("Kiran", 1);
        myHashMap.put("Anant", 2);
        myHashMap.put("Akshita", 3);
        myHashMap.put("Ankit", 4);

        System.out.println("get=" + myHashMap.get("Kiran"));
        System.out.println("remove=" + myHashMap.remove("Kiran"));
        System.out.println("containsKey=" + myHashMap.containsKey("Kiran"));
        System.out.println("containsKey=" + myHashMap.containsKey("Akshita"));
        System.out.println("Count=" + myHashMap.size());
    }
}
