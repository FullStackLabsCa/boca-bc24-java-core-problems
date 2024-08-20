package problems.string;

public class RemoveVowels {
    public static String removeVowels(String input){
        System.out.println("Input: " + input);
        String replaced = input.replaceAll("[aieouAIEOU]", "");
        return replaced;
    }

    public static void main(String[] args) {
        System.out.println(removeVowels("HellO World"));
    }

}
