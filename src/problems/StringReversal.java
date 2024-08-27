package problems;

public class StringReversal {
    public static String stringReveral(String toReverse){
        String reversedString = "";

        char seperatedCharacters[] = new char[toReverse.length()];

        for(int i = 0; i < toReverse.length(); i++){
            seperatedCharacters[i] = toReverse.charAt(toReverse.length() - i - 1);
        }

        for(char character : seperatedCharacters){
            reversedString = reversedString + character;
        }

        return reversedString;
    }

    public static void main(String[] args) {
        StringReversal reverseString = new StringReversal();

        System.out.println(reverseString.stringReveral("Akshat"));
    }
}
