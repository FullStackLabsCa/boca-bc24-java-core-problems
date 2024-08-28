package problems.stringarrayoops;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RemoveVowels {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        StringBuffer str = removeVowels("Hello World");
        System.out.println("String without vowels:"+str);
    }

    public static StringBuffer removeVowels(String str) {
//        str = str.replaceAll("[aeiouAEIOU]","");
//        char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        String vowels = "aeiouAEIOU";
        System.out.println("Given string : "+str);
        StringBuffer newString= new StringBuffer("");
        for(char character: str.toCharArray()) {
                if (!vowels.contains(String.valueOf(character))){
                    newString.append(character);

            }

        }


        return newString;

    }



}