package arrays;

public class Mergetwoarrays {
    public static void main(String[] args) {
        mergeArrays(new int[]{1, 2}, new int[]{3, 4});
    }

    private static void mergeArrays(int[] a, int[] b) {
        int[] c = new int[a.length+b.length];
        System.arraycopy(a,0, c,0, a.length);
        System.arraycopy(b,0, c,a.length, b.length);
        for(int i: c){
            System.out.println(i);
        }

    }
}
