package io.reacticestax.stringsandarraysproblems;

public class CountVowelsInString {
    public static int countVowels(String inputString) {
        inputString = inputString.toLowerCase();
        String[] vowels = {"a", "e", "i", "o", "u"};

        int count = 0;

        if (inputString.isEmpty()) {
            return 0;
        }

        String[] arr = inputString.split("");
        String[] arrayOfVowelsInInputString = new String[arr.length];

        for (String str : arr) {
            for (String vowel : vowels) {
                if (str.contains(vowel)) {
                    arrayOfVowelsInInputString[count] = vowel;
                    count++;
                }
            }
        }


        String[] newArrayWithRemovedNullValues = new String[count];

        for (int i = 0; i < count; i++) {
            newArrayWithRemovedNullValues[i] = arrayOfVowelsInInputString[i];
            System.out.println("array element " + i + " :" + newArrayWithRemovedNullValues[i]);
        }

        return count;
    }
}
