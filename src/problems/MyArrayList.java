package problems;

import java.util.ArrayList;
import java.util.List;

public class  MyArrayList <T> {
    List<T> ts;
    public static void main(String[] args) {

    }

    public MyArrayList(){
        this.ts = new ArrayList<>(10);
    }
    public MyArrayList(int capacity){
        this.ts = new ArrayList<>(capacity);
    }

    public void add(T element){
        ts.add(element);
    }
    
    public int size(){
        return ts.size();
    }
    
    public <T> Object get(int position){
        return ts.get(position);
    }

    public void remove(int position){
        ts.remove(position);
    }


}
