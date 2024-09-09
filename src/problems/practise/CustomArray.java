package problems.practise;

public class CustomArray {
    static int[] intArray = new int[5];
    static boolean[] booleansArray = new boolean[5];

    public static void main(String[] args) {
        intArray[0] = 1;
        booleansArray[0] = true;
        System.out.println("index: "+ intArray[1]);
        System.out.println("boolean index: "+ booleansArray[2]);
    }
}
