package collections.maps;

import linkedList.EmptyListException;

public class MyHashMap<K, V> {

    //    Array of Buckets
//    Each Bucket is LinkedList
//            Containing Key-Value Pair
    private Node<K,V>[] myHashMap;
    private int capacity = 16;
    private int threshold = (int) Math.floor(capacity * 0.75);
    private int size = 0;

    public MyHashMap() {
        myHashMap = new Node[capacity];
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        myHashMap = new Node[capacity];
    }

    //Adding
    public void put(K key, V value){
//        resize(); //Needs rehashing

        //Adding Null key
        if(key == null){
            Node<K,V> top = myHashMap[0];
            //if top is empty
            if(top == null){
                myHashMap[0] = new Node<>(null, value);
            } else {
                //Add to the last
                while(top != null){
                    if(top.getKey() == null){
                        top.setValue(value);
                        return;
                    }
                    top = top.getNextNode();
                }
                top.setNextNode(new Node(null, value));
            }
            size++;
            return;
        }

        //Get BucketIndex
        int bucketIndex = getBucketIndex(key);

        Node<K,V> bucketOfInterest = myHashMap[bucketIndex];

        //If Top is empty
        if(bucketOfInterest == null){
            //Add the top of the linkedList
            myHashMap[bucketIndex] = new Node<>(key, value);
            size++;
        }
        //If LinkedList is not empty
        else {
            Node<K,V> lastNode = null;
            //Append through Items in the linkedList
            while(bucketOfInterest != null) {
                //Check if key matches to any of the keys in the linked list
                if((bucketOfInterest.getKey()) == (key)){ // Need some though on what comparison operator to use
                    //If so, update the value
                    bucketOfInterest.setValue(value);
                    size++;
                    return;
                } // else
                lastNode = bucketOfInterest;
                bucketOfInterest = bucketOfInterest.getNextNode();
            }
            //If no matching was found till the end of the list
            //Add the K-V pair to the end of the linkedList
            lastNode.setNextNode(new Node<>(key,value));
            size++;
        }
    }

    public int getBucketIndex(K key){
        int hashCode = key.hashCode();
        return (hashCode % capacity);
    }

    //Retrieving
        //Get
        //Retrieve the value associated with the key
    public V get(K key){

        if(key == null){
            Node<K,V> top = myHashMap[0];

            if(top == null){
                return null;
            } else
            //if top is empty
            if(top.getKey() == null){
                return top.getValue();
            } else {
                //Add to the last
                while(top!= null){
                    if(top.getKey() == key){
                        return top.getValue();
                    }
                    top = top.getNextNode();
                }
                return null;
            }
        }

        int bucketIndex = getBucketIndex(key);

        Node<K,V> bucketOfInterest = myHashMap[bucketIndex];

        if(bucketOfInterest == null){
            return null;
//            throw new KeyNotFoundException("Key Bucket Does Not Exist!");
        } else{
            while (bucketOfInterest != null){
                if(bucketOfInterest.getKey() == key) {
                    return bucketOfInterest.getValue();
                }
                bucketOfInterest = bucketOfInterest.getNextNode();
            }
            return null;
//            throw new KeyNotFoundException("Key Not Found");
        }
    }

    //Removing
        //Remove the K-V Pair based on the key
    public V remove(K key){

        if(key == null){
            V value = null;
            Node<K, V> top = myHashMap[0];
            if(top == null){
                return null;
            } else {
                //If First Element is Null
                if(top.getKey() == null) {
                    value = top.getValue();
                    Node<K,V> nextNode = top.getNextNode();
                    myHashMap[0] = nextNode;
                    size--;
                    return value;
                } else if (top.getNextNode() == null) {
                    System.out.println("Null Key Not Found");
                    return null;
                } else {
                    Node<K,V> previousNode = null;
                    while (top != null) {
                        if (top.getKey() == null) {
                            value = top.getValue();
                            previousNode.setNextNode(top.getNextNode());
                            size--;
                            return value;
                        }
                        previousNode = top;
                        top = top.getNextNode();
                    }
                }
            }

            size--;
            return value;
        }

        int bucketIndex = getBucketIndex(key);

        Node<K,V> bucketOfInterest = myHashMap[bucketIndex];
        Node<K,V> previousNode = null;
        Node<K,V> nextNode;
        V value = null;

        if(bucketOfInterest != null){
            while(bucketOfInterest != null){
                if((bucketOfInterest.getKey()) == (key)){
                    //Only 1 element
                    if(bucketOfInterest.getNextNode() == null){
                        value = bucketOfInterest.getValue();
                        myHashMap[bucketIndex] = null;
                        size--;
                        return value;
                    } else {
                        value = bucketOfInterest.getValue();
                        nextNode = bucketOfInterest.getNextNode();
                        previousNode.setNextNode(nextNode);
                        size--;
                        break;
                    }
                }
                previousNode = bucketOfInterest;
                bucketOfInterest = bucketOfInterest.getNextNode();
            }
            return value;
        } else {
            return null;
//            throw new KeyNotFoundException("Key Not Found");
        }
    }

    //Check if the map contains a given key
    public boolean containsKey(K key){

        if(key == null){
            Node<K,V> nodeWithNull = myHashMap[0];

            if(nodeWithNull == null){
                return false;
            } else {
                while(nodeWithNull != null){
                    if(nodeWithNull.getKey() == null) return true;

                    nodeWithNull = nodeWithNull.getNextNode();
                }
            }
            return false;
        }

        //Get bucket index
        int bucketIndex = getBucketIndex(key);

        //On that bucket check if the linkedlist contains the key
        Node<K, V> linkedListOnInterestedIndex = myHashMap[bucketIndex];

        //Iterate through the linked list
        while(linkedListOnInterestedIndex != null){
            //if given key is equal to any of the key in list
            if((linkedListOnInterestedIndex.getKey()) == key){ // This needs some though about what comparator operator to use

                return true;
            }
            linkedListOnInterestedIndex = linkedListOnInterestedIndex.getNextNode();
        }

        //else after iteration is finished
        return false;
    }

    //Size
        //Return the number of KV Pairs in the list.
    public int size(){
        if(size >= 0) {
            return size;
        } else {
            throw new EmptyListException("collection does not exist / Null");
        }
    }

    //Dynamic Resizing (Optional)
        //When reached certain threshold
        //Resize the bucket Array
    public void resize(){
        if (size > threshold){
            //Update the capacity Variable
            capacity = capacity*2;
            threshold = (int) Math.floor(capacity * 0.75);

            //array copy to a temp array
            Node<K,V>[] tempArray = myHashMap;

            //Double the number of buckets in the array
            myHashMap = new Node[capacity];

            //copy the tem array to the newSizedArray
            System.arraycopy(tempArray, 0, myHashMap, 0, size);

            //NEED REHASHING SOMEWHERE IN THIS METHOD - A TOPIC FOR LATER
        }
    }

    //Collision Resolution - Chaining with LinkedList

    public static void main(String[] args) {
        MyHashMap<String, Integer> test1 = new MyHashMap<>();

        test1.put("Akshat1", 1);
        test1.put("Akshat2", 1);
        test1.put("Akshat3", 1);
        test1.put("Akshat4", 1);
        test1.put("Akshat5", 1);
        test1.put("Akshat11", 1);
        test1.put("Akshat6", 1);
        test1.put("Akshat7", 1);
        test1.put("Akshat8", 1);
        test1.put("Akshat9", 1);
        test1.put("Akshat10", 1);
        test1.put("Akshat11", 1);
        test1.put("Akshat12", 1);
        test1.put("Akshat13", 1);
        test1.put("Akshat14", 1);
        test1.put("Akshat15", 1);
        test1.put("Akshat16", 1);
        test1.put(null, 10);
        System.out.println("test1.size() = " + test1.size());

        System.out.println("test1.checkKeyExist(\"Akshat20\") = " + test1.containsKey("Akshat20"));
        System.out.println("test1.checkKeyExist(\"Akshat10\") = " + test1.containsKey("Akshat10"));
        System.out.println("test1.containsKey(null) = " + test1.containsKey(null));

        System.out.println("test1.remove(\"Akshat10\") = " + test1.remove("Akshat10"));

    }

}
