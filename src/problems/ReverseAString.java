package problems;

public class ReverseAString {
    public static void main(String[] args) {
        String input =  "Ankit";
        StringBuilder sb = new StringBuilder(input);
        String output = sb.reverse().toString();
        System.out.println(output);
    }
}
