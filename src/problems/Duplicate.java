package previous;

public class Duplicate {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,3,6,6,4};
        int[] generated = {arr.length};
        for (int i = 0; i <= arr.length; i++){
            if(generated[i] == arr[i]) {
//                System.out.println(generated[0]);
            }
        }
        System.out.println(generated[0]);
    }
}
