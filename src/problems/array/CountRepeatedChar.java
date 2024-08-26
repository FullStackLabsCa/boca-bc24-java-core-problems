package problems.array;

public class CountRepeatedChar {

    public static void main(String[] args) {
//        12 Read a given string and count how many times a given alphabet or number is repeated

        String name = "ANIL KUMAR aa";
        char targetChar = 'A' ;
        int count = 0;

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) ==  targetChar) {
                count++;
            }
        }
        System.out.println(targetChar + " repeated "+count+" times");


    }
}
