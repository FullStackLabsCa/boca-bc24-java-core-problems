package generics;

public class CountOccurrences {

    public static <T> int countOccurrences(T[] array, T element){
        int occurences = 0;
        for( T arrayElement : array){
            //Use .equals when element != null
            if(element != null && arrayElement != null) {
                if (arrayElement.equals(element)) { // Using .equals because I want to check the hashCode / Value being equal or not rather than the memory reference
                    occurences++;
                }
            } else { //Use == when element == null
                if (arrayElement == element){
                    occurences++;
                }
            }



        }
        return occurences;
    }

    public static void main(String[] args) {
        Number[] myList = new Integer[3];

        myList[0] = 1;
        myList[1] = 2;
        myList[2] = 2;

        System.out.println(CountOccurrences.countOccurrences(myList, 2));
    }
}
