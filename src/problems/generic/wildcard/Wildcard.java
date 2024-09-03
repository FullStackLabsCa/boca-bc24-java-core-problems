package problems.generic.wildcard;

import java.util.List;


//mostly wildcards are given to the methods not a class
// used as a producer that  is used to read the data not writing the data
// used in generics when we don't know the specific type but we can bound it like normal generics type T(? extends Number)

//@pecs producer extends and consumer super

public class Wildcard {
   public static void addElement(List<? extends  Number> list){
        for (Number numb:list){
            System.out.println(numb);
        }
    }

    public static void main(String[] args) {
//        addElement();
    }



}
