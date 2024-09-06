import java.util.ArrayList;
import java.util.List;

public class MyArrayList<T> {
    private List<T> arrayElement;
    private int size;


    public MyArrayList() {
        this.arrayElement = new ArrayList<>(10);
    }

    public MyArrayList(int capacity) {
        this.arrayElement = new ArrayList<>();
        this.size = size;
    }

    public void Addelements(T element) {
        arrayElement.add(element);
        size++;
        System.out.println("Array size: "+arrayElement);
    }

    public void resizeArray() {
    }


    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
      for (int i=0;i<5;i++){
          myArrayList.Addelements(i);
      }
      MyArrayList<String> myStringList = new MyArrayList<>();
      for (int i=0; i<5;i++){
          myStringList.Addelements("Apple");
          myStringList.Addelements("Banana");
      }
    }
}
