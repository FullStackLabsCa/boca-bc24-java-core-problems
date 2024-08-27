//package problems;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class OddOccurrencesInGenericCollections {
//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(1);
//        list.add(3);
//        System.out.println("Occurences of Odd => " + countOddIntegers(list));
//    }
//    public static <T extends Collection<Integer>> int countOddIntegers(T t){
//        int count = 0;
//
//        for(Integer i : t){
//            if(i%2 != 0){
//                count ++;
//            }
//        }
//        return count;
//    }
//}
