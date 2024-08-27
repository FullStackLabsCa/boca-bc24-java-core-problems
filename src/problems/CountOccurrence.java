package problems;

public class CountOccurrence {
    public static void main(String[] args) {

        String[] input = new String[4];
        input[0] = "Abhay";
        input[1] = "Krish";
        input[2] = "Abhay";
        input[3] = "Nimavat";
        System.out.println(countOccurrences(input, "Abhay"));

    }
    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;

        for(T i:array){
            if(i==element){
                count++;
            }
        }
        return count;
    }

}
