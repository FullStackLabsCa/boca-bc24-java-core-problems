package genricproblem;

public class CountOccurrences {
    public static void main(String[] args) {

        String[] name = {"John", "Alex", "Nick", "Alex", "Brat", "Lee", "Alex"};
        String strArray = "Alex";
        int repeatedString = countOccurrences(name, strArray);
        System.out.println(strArray + " repeated: " + repeatedString + " times");

        Integer[] numbers = {2, 32, 23, 23, 43, 22, 23, 23, 6, 54, 354, 23};
        Integer checkNumber = 23;
        Integer repeatedNumber = countOccurrences(numbers, checkNumber);
        System.out.println(checkNumber + " repeated " + repeatedNumber + " times");

        Character[] characters = {'r', 'f', 'u', 'f', 'f'};
        Character character = 'f';
        Integer repeatedchar = countOccurrences(characters, character);
        System.out.println(character + " repeated " + repeatedchar + " times");
    }

    public static <T> int countOccurrences(T[] array, T element) {

        int count = 0;
        for (T input : array) {
            if (input == null) {
                return 0;
            }
            if (element == null) {
                return 2;
            }
            if (input.equals(element)) {
                count++;
            }
        }
        return count;
    }
}

