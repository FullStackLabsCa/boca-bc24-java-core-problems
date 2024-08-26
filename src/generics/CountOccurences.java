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
}
