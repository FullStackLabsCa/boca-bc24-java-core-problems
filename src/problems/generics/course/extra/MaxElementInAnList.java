//package problems.generics.course.extra;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class MaxElementInAnList {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("What size of an array do you need? ");
//        int size= scanner.nextInt();
//
//        List<Number> list= new ArrayList<>();
//        scanner.nextLine();
//
//        System.out.println("Enter elements one by one: ");
//        for(int i=0; i<size; i++){
//            list.add(i, scanner.nextInt());
//        }
//
//        System.out.println(maxElement(list));
//
//        scanner.close();
//    }
//    public static <T extends Number>  int maxElement(List<Number> l){
//        Number max=0;
//        for(int i=0; i<l.size(); i++){
//            if(max< l.get(i).intValue()){
//                max= l.indexOf(i);
//            }
//        }
//        return max;
//    }
//}
