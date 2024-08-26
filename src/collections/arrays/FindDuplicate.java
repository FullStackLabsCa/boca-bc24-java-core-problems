package collections.arrays;

public class FindDuplicate {

    public static int[] findDuplicates(int[] inputArray){

        int[] temp = new int[inputArray.length];
        int count = 0;

        for (int i = 0; i < inputArray.length; i++) {
            //Checking with all the numbers ahead
            for (int j = i+1; j < inputArray.length; j++) {
                if(inputArray[j] == inputArray[i]){

                    //Check with the repeating numbers too
                    boolean alreadyExist = false;
                    for(int k = 0; k < temp.length ; k++) {
                        if(inputArray[j] == temp[k]){
                            alreadyExist = true;
                        } else {
                            alreadyExist = false;
                        }

                        if(!alreadyExist) {
                            temp[count] = inputArray[i];
                            count++;
                        }
                        break;
                    }
                }
            }
        }

        int[] duplicates = new int[count];
        System.arraycopy(temp,0,duplicates,0,count);

        return duplicates;
    }

    public static void main(String[] args) {
        int[] resultingArray = FindDuplicate.findDuplicates(new int[]{5,5,5,5});

        for(int i : resultingArray){
            System.out.println(i);
        }
    }
}
