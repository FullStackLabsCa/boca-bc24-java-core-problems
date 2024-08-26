package generics;//

public class CountOcccurence {

    public static <T> int countOccurrences(T[] tTypeArray, T element) {
        int count = 0;

        for(T arrayElement: tTypeArray) {
            if (arrayElement == null && element == null) {
                count++;
            } else if (arrayElement != null && arrayElement.equals(element)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Integer[] intArray = new Integer[]{1, 2, 2, 3, 5, 4, 5, 5};
        String[] stringArray = new String[]{"Car", "car", "bike", "bike", null, "BIKE", "Truck", null};
        System.out.println("countOccurrences(intArray, element)::" + countOccurrences(intArray, 5));
        System.out.println("countOccurrences(stringArray, element)::" + countOccurrences(stringArray, "bike"));
        System.out.println("countOccurrences(stringArray, element) with null check::" + countOccurrences(stringArray, null));
    }
}
