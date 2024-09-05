import java.lang.reflect.Array;
import java.util.Arrays;

public class demo {
    public static void main(String[] args) {
        String name = "Karan + (31 + (24 23) + ( 34 + 43)";
        System.out.println("name.contains(\"y\") = " + name.contains("y"));
        System.out.println("name.contains(\"a\") = " + name.contains("a"));

        String[] split = name.split("\\s");
        System.out.println("split = " + Arrays.toString(split));
    }

}
