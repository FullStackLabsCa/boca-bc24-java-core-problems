package problems;

public class CountVowels {
    public static void main(String[] args) {

   System.out.println(countVowels("Hello world"));
//        removeVowel("HE llo");

    }
    public static int countVowels(String input){

        char c;
        int count = 0;
        if(input==null|| input.isEmpty()){
            System.out.println("Null or Empty values not allowed");

        }else{
        for(int i=0;i<input.length();i++){
            c = input.toLowerCase().charAt(i);
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
                count++;
                System.out.print(c+" ");
            }
        }
        }
        System.out.println();
        return count;

    }
    public static void removeVowel(String input) {
        String newInput = input.toLowerCase().replace("a","")
                .replace("e","")
                .replace("i","")
                .replace("o","")
                .replace("u","");
        System.out.println(newInput);
        }

}
