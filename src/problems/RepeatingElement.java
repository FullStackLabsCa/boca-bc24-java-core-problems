package problems;

public class RepeatingElement {
    public static void main(String[] args) {
        repeatedElement("Hello");
    }
    public static void repeatedElement(String s) {
        char[] chars = s.toCharArray();
        int[] counts = new int[256];
        for (char c : chars) {
            counts[c]++;
        }
        System.out.print("Repeated characters: ");
        boolean hasRepeated = false;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 1) {
                System.out.print((char) i + " ");
                hasRepeated = true;
            }
        }

        if (!hasRepeated) {
            System.out.println("None");
        }
    }
    }