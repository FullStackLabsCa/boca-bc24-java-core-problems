//package problems.generics.countOddIntegers;
//
//import java.util.*;
//
//public class MainRunner {
//    public static void main(String[] args) {
////        TreeSet<Integer> integerCollectionTreeSet = new TreeSet<>();
////            integerCollectionTreeSet.add(456);
////            integerCollectionTreeSet.add(345);
////            integerCollectionTreeSet.add(123);
////            integerCollectionTreeSet.add(234);
////            integerCollectionTreeSet.add(5678);
////
//////            System.out.println("Integer TreeSet = " + integerCollectionTreeSet);
//
//        OddCounter oddInteger = new OddCounter();
//        Scanner input = new Scanner(System.in);
//        List<Integer> numbersList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        Set<Integer> numbersSet = Set.of(11, 22, 33, 44, 55, 66);
//
//        System.out.println("Enter the SET elements:");
//        System.out.println("How many elements you want to enter:");
//        int collectionSize = input.nextInt();
//        Set<Integer> numbersSet2 = new HashSet<>(Set.of());
//        for (int i=0; i<collectionSize;i++){
//            System.out.println("Enter the elements " + (i + 1) + ":");
//            int element = input.nextInt();
//                numbersSet2.add(element);
//        }
//        int oddCountSet = oddInteger.countOdds(numbersSet2);
//        System.out.println("Number of odd integers in the set: " + oddCountSet);
//
//        TreeSet<Integer> numbersTreeSet = new TreeSet<>();
//        numbersTreeSet.addAll(Arrays.asList(123, 243, 534, 32, 121, 332, 5363));
//
//        int oddCountList = oddInteger.countOdds(numbersList);
//        System.out.println("Number of odd integers in the list: " + oddCountList);
////
////        int oddCountSet = oddInteger.countOdds(numbersSet);
////        System.out.println("Number of odd integers in the set: " + oddCountSet);
//
////        int oddCountSet2 = oddInteger.countOdds(numbersSet2);
////        if(numbersSet2 == null||numbersSet2.size() == 0){
////            System.out.println("Collection is null");
////        }else
////        System.out.println("Number of odd integers in the number set: " + oddCountSet2);
//
//        int oddCountTreeSet = oddInteger.countOdds(numbersTreeSet);
//        System.out.println("Number of odd Intgers in the TreeSet: "+oddCountTreeSet);
//    }
//}
