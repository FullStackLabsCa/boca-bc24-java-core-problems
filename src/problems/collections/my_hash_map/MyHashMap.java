package problems.collections.my_hash_map;

import java.util.LinkedList;
import java.util.Objects;

public class MyHashMap<K, V> {

  private final LinkedList<Entry<K, V>>[] buckets;  // Each bucket is a list of entries
  private int size;

  public MyHashMap() {
    buckets = new LinkedList[16];  // Initialize the array of lists with 16 slots
    size = 0;
  }

  private int getBucketIndex(K key) {
    if (key == null) {
      return 0;  // Use bucket 0 for null keys
    } else {
      return key.hashCode() % buckets.length;  // Compute the bucket index for non-null keys
    }
  }


  // Add a key-value pair to the map (or update it if the key exists)
  public void put(K key, V value) {
    int bucketIndex = getBucketIndex(key);

    if (buckets[bucketIndex] == null) {
      buckets[bucketIndex] = new LinkedList<>();
    }

    // Check if the key already exists and update it
    for (Entry<K, V> entry : buckets[bucketIndex]) {
      if (Objects.equals(entry.key, key)) {
        entry.value = value;  // Update the existing value
        return;
      }
    }

    // If the key does not exist, add a new entry
    buckets[bucketIndex].add(new Entry<>(key, value));
    size++;
  }

  // Get the value for a given key
  public V get(K key) {
    int bucketIndex = getBucketIndex(key);
    LinkedList<Entry<K, V>> bucket = buckets[bucketIndex];

    if (bucket == null) return null;

    // Search for the key in the bucket
    for (Entry<K, V> entry : bucket) {
      if (Objects.equals(entry.key, key)) {
        return entry.value;
      }
    }

    return null;
  }

  // Remove a key-value pair and return the value
  public V remove(K key) {
    int bucketIndex = getBucketIndex(key);
    LinkedList<Entry<K, V>> bucket = buckets[bucketIndex];

    if (bucket == null) return null;

    // Iterate over the bucket to find and remove the entry
    for (Entry<K, V> entry : bucket) {
      if (Objects.equals(entry.key, key)) {
        V value = entry.value;
        bucket.remove(entry);
        size--;
        return value;
      }
    }

    return null;
  }

  // Return the number of key-value pairs in the map
  public int size() {
    return size;
  }

  // Check if the map contains a key
  public boolean containsKey(K key) {
    return get(key) != null;
  }
}
