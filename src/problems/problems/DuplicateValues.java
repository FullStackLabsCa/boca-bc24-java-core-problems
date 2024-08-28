package problems.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateValues {
    public static void main(String[] args) {


        Scanner Obj = new Scanner(System.in);
        String line = Obj.nextLine();

        HashMap<Character, Integer> map = counter(line);
        System.out.println("Character frequencies:");
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + "------> " + entry.getValue()+" : Duplicatevalue");
                break;
            }
            System.out.println("Character: '" + entry.getKey() + "', Frequency: " + entry.getValue());
        }




    }
    public static HashMap<Character, Integer> counter(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}