package problems;

public class FindDuplicate {

    public int findFirstDuplicate(int[] numbers){

        int[] tempHold = new int[numbers.length];
        int tempHoldIteration = 0;

        for(int num : numbers){
            for(int tempNum : tempHold){
                if (num == tempNum){
                    return tempNum;
                }
            }
            tempHold[tempHoldIteration] = num;
            tempHoldIteration++;
        }
        return 0;
    }

    public static void main(String[] args) {
        FindDuplicate test = new FindDuplicate();

        System.out.println(test.findFirstDuplicate(new int[]{1, 2, 1, 2, 1, 3}));;
    }
}
