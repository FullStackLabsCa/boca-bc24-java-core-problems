package problems.collections.hashmap;


@SuppressWarnings("java:S106")
public class MyHashMap<K, V> {
    int capacity = 5;
    private final Entry<K, V>[] table;
    int count = 0;

    public MyHashMap() {
        this.table = new Entry[capacity];
    }

    public void put(K key, V value) {
        int index = 0;
        if (key == null) {
            index = 0;
        } else {
            index = key.hashCode() % capacity;
        }
        Entry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<K, V>(key, value);
            count++;
        } else {
            while (entry.next != null) {
                if (entry.getKey() == key) {
                    entry.setValue(value);
                    System.out.println("The value for '" + key + "' should be updated to " + value);
                    return;
                }
                entry = entry.next;
            }
            if (entry.getKey() == key) {
                entry.setValue(value);
                System.out.println("The value for '" + key + "' should be updated to " + value);
            } else {
                entry.next = new Entry<K, V>(key, value);
                count++;
            }
        }
    }

    public V get(K key) {
        int index = 0;
        if (key == null) {
            index = 0;
        } else {
            index = key.hashCode() % capacity;
        }
        Entry<K, V> entry = table[index];
        if (entry == null) {
            return null;
        }

        while (entry != null) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = 0;
        if (key == null) {
            index = 0;
        } else {
            index = key.hashCode() % capacity;
        }
        Entry<K, V> entry = table[index];
        Entry<K, V> previousEntry = entry;

        if(size() == 1 && entry.getKey() == key){
            table[index] = null;
            count--;
            return previousEntry.getValue();
        }

        while (entry.getKey() != null && entry.next != null) {
            previousEntry = entry;
            entry = entry.next;
        }
        if (entry.getKey() == key) {
            previousEntry.next = entry.next;
            table[index] = null;
            count--;
            return entry.getValue();
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = key.hashCode() % capacity;
        Entry<K, V> entry = table[index];
        if (entry == null) {
            return false;
        }

        while (entry != null) {
            if (entry.getKey() == key) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> stringIntegerMyHashMap = new MyHashMap<>();
        stringIntegerMyHashMap.put("Apple", 10);
        stringIntegerMyHashMap.put(null, 10);
//        stringIntegerMyHashMap.put("Orange", 10);
//        stringIntegerMyHashMap.put("Litchi", 10);
//        stringIntegerMyHashMap.put("Mango", 10);
//        stringIntegerMyHashMap.put("Plum", 10);
    }
}
