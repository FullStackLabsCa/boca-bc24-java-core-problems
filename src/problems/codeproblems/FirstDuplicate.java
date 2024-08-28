package problems.codeproblems;

public class FirstDuplicate {
    public static void main(String[] args) {

        int[] arrayDuplicate = {1, 0, 2, 8, 8, 1, 7, 1};
        int count = 0;
        int emptyArray[] = new int[1];
        boolean duplicateDetected = false;
        for (int i = 0; i < arrayDuplicate.length; i++) {
            for (int j = i + 1; j < arrayDuplicate.length; j++) {
                if (arrayDuplicate[i] == arrayDuplicate[j]) {
                    emptyArray[0] = arrayDuplicate[i];
                    duplicateDetected = true;
                    break;
                }
            }
            if (duplicateDetected) {
                break;
            }
        }
        if (duplicateDetected){
            System.out.println("First duplicate is: " + emptyArray[0]);
        }

    }
}


