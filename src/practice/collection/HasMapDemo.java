package practice.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HasMapDemo {
    public static void main(String[] args) {

        Map<String , Integer> stringIntHashMap = new HashMap<>();
        System.out.println("========================================");

        stringIntHashMap.put("1). ", 10);
        stringIntHashMap.put("1). ", 30);
        System.out.println("stringIntHashMap.put(\"1). \", 10) = " + stringIntHashMap.put("1). ", 10));
        System.out.println("stringIntHashMap.put(\"1). \", 30) = " + stringIntHashMap.put("1). ", 30));
//
//        System.out.println("========================================");
//
//        System.out.println(stringIntHashMap.put("a", 1));
//        System.out.println(stringIntHashMap.put("a", 2));
//        System.out.println("stringIntHashMap.get(\"a\") = " + stringIntHashMap.get("a"));
//        System.out.println("========================================");
//
//        System.out.println("stringIntHashMap.put(\"b\" ,2) = " + stringIntHashMap.put("b", 2));
//        System.out.println("stringIntHashMap.put(\"b\", 3) = " + stringIntHashMap.put("b", 3));
//
        String stringInput = "aaabbcbsjsajnjjssa";
        char[] inputCharArray = stringInput.toCharArray();

//        for (Character c: inputCharArray) {
//            if (stringIntHashMap.containsKey(c)) {
//                stringIntHashMap.put(c, stringIntHashMap.get(c)+1);
//            } else stringIntHashMap.put(c,1);
//        }
        System.out.println(stringIntHashMap);
        List<Character> list = stringInput.chars()
                .mapToObj(v -> (char) v)
                .collect(Collectors.toList());

    }
}