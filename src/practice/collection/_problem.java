package practice.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _problem {
    //Input: "1 1 1 1 5 5 2 2 2 2 2 4 4 4 9 9 9 "
    //Output: "5 5 4 4 4 9 9 9 1 1 1 1 2 2 2 2 2"
    public static void main(String[] args) {
        String str = "1 1 1 1 5 5 2 2 2 2 2 4 4 4 9 9 9 ";
        System.out.println(Arrays.stream(str.split("\\s")).distinct().toList());

        List<Integer> collect = Stream.of(str.split("\\s")).map(Integer::valueOf).collect(Collectors.toList());

        System.out.println(collect);
    }
}
