package loopproblems;

public class SumArrayEven {
    public static void main(String[] args) {
        int[] array = {2,7,9,5,4,8};
        int sum = 0;
        for (int i=0; i<array.length; i++){
            if(i%2 == 0){
                sum = sum + array[i];
            }
        }
        System.out.println("Sum of even number: "+sum);
    }
}
