package io.reacticestax.stringsandarraysproblems;

public class Main {

    public static void main(String[] args) {

//        Scanner intake = new Scanner(System.in);
//        System.out.println("Enter a string: ");
//        String inputString = intake.nextLine();

        //  int vowelCount = CountVowelsInString.countVowels(inputString);
        //  System.out.println("number of vowels : " + vowelCount);

        //String newStrWithoutVowels = RemoveVowelsInString.removeVowels(inputString);
        //System.out.println(" new String: " + newStrWithoutVowels);


        //LongestWordInString.findLongestWordInString(inputString);

        // CapitalFirstWordOfString.capitalizeWord(inputString);

        // System.out.println(SortedArray.isSorted(new int[]{2, 9,8, 5, 6, 6}));


//        int[] mergedArray = (MergeTwoArrays.mergeArray(new int[]{2, 9,8, 5, 6, 6}, new int[]{233,22,24}));
//        for (int i = 0; i < mergedArray.length; i++) {
//            System.out.print(mergedArray[i]);
//            if (i < mergedArray.length - 1) {
//                System.out.print(", ");
//            }
//        }


        int[] arrayOfDupes = (DuplicateNumInArray.findDuplicateNum(new int[]{2, 9 ,9 , 9  , 6, 6}));
        for (int i = 0; i < arrayOfDupes.length; i++) {
            System.out.print(arrayOfDupes[i]);
            if (i < arrayOfDupes.length - 1) {
                System.out.print(", ");
            }
        }

    }

}
