package oldproblems.MaximumNumber;

public class Maxnum {
    private int[] numArray = new int[10];
    public void findMax(int[] numArray){
        int max = numArray[0];
        for (int i = 1; i < numArray.length; i++) {
            if (numArray[i] > max)
                max = numArray[i];
        }
        System.out.println("Maximum number is: "+max);

    }
}
