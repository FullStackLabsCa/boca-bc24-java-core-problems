package problems.array;

public class ReverseString {

    public static void main(String[] args) {

        String name = "Anil kumar";
        System.out.println(reverseString(name));
    }
    public static String reverseString(String name){
            StringBuilder reverse = new StringBuilder();
            for (int i = name.length() - 1; i >= 0; i--) {
                reverse.append(name.charAt(i));
            }
            return reverse.toString();
    }
}
