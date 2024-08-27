package generics;

public class CountOccurences {

    public static <T> int countOccurences(T[] array, T element){
        int occurences = 0;
        for( T arrayElement : array){
            if(arrayElement.equals(element)){ // Using .equals because I want to check the hashCode / Value being equal or not rather than the memory reference
                occurences++;
            }
        }
        return occurences;
    }

    public static void main(String[] args) {
        Number[] myList = new Integer[3];

        myList[0] = 1;
        myList[1] = 2;
        myList[2] = 2;

        System.out.println(CountOccurences.countOccurences(myList, 2));
    }
}
