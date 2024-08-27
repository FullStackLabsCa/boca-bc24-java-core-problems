package problems.generics.normal;

import java.util.*;

public class CountOddIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements you would like to add: ");
        int size = scanner.nextInt();

        Integer[] arr= new Integer[size];

        System.out.println("Enter elements one by one: ");
        for (int i = 0; i < size; i++) {
            int element = scanner.nextInt();
            arr[i]= element;
        }

        System.out.println("Number of Odd elements in the given collection is: " + countOddIntegers(Arrays.asList(arr)));
        System.out.println("Type of the collection is: " + arr.getClass().getSimpleName());
    }

        public static <T> int countOddIntegers(Collection<T> collection) {
            int count = 0;
            for(T element: collection){
                if(element instanceof Integer){
                    if((Integer)element%2!=0){
                    count++;
                    }
                }
            }
            return count;
    }
}