package genericsProblems;



public class Main {
    public static void main(String[] args) {
        //OccurrencesInArray
        Integer[] intArray = {1,2,3,4,5,5,6,8,6};
        System.out.println(CountOccurrences.countOccurrences(intArray,5));

        String[] stringArr = { "apple" ,"apple" ,"apple" ,"apple" ,"apple" ,"banana","apple" };
        System.out.println(CountOccurrences.countOccurrences(stringArr,"apple"));

        String[] arrayWithNull = {"a", null, "b", null, "c"};
        System.out.println(CountOccurrences.countOccurrences(arrayWithNull,null));






    }



    }


