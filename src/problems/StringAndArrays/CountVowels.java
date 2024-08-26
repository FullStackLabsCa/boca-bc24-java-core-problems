package problems.StringAndArrays;

public class CountVowels {

    public static void main(String[] args) {

        String input = "I love programming in Java";
        System.out.println("Vowels Count : " + countVowels(input));


    }

    public static int countVowels(String input) {
        int[][] vowelOccurance = new int[5][2];
        char[][] vowelOccuranceAlps = new char[5][2];

        int aCount=0, eCount=0, iCount=0, oCount=0, uCount=0;
        int count = 0;
        input = input.toLowerCase();
        if (input.isEmpty()) return 0;
        else {
            for (int i = 0; i < input.length(); i++) {
                if (checkVowelsChar(input.charAt(i))) {
                    count += 1;
                    if(input.charAt(i) == 'a'){
                        aCount = aCount+1;
                        vowelOccuranceAlps[0][0] = 'A';
                        vowelOccurance[0][1] = aCount;
                    }else if(input.charAt(i) == 'e'){
                        eCount = eCount+1;
                        vowelOccuranceAlps[1][0] = 'E';
                        vowelOccurance[1][1] = eCount;
                    }else if(input.charAt(i) == 'i'){
                        iCount = iCount+1;
                        vowelOccuranceAlps[2][0] = 'I';
                        vowelOccurance[2][1] = iCount;
                    }else if(input.charAt(i) == 'o'){
                        oCount = oCount+1;
                        vowelOccuranceAlps[3][0] = 'O';
                        vowelOccurance[3][1] = oCount;
                    }else if(input.charAt(i) == 'u'){
                        uCount = uCount+1;
                        vowelOccuranceAlps[4][0] = 'U';
                        vowelOccurance[4][1] = uCount;
                    }
                }
            }

        }
        for (int i = 0; i < vowelOccuranceAlps.length; i++) {
            System.out.println(vowelOccuranceAlps[i][0] + " " + vowelOccurance[i][1]);
        }
        return count;
    }
    private static boolean checkVowelsChar(char input) {
        char aChar = 'a';
        char eChar = 'e';
        char iChar = 'i';
        char oChar = 'o';
        char uChar = 'u';

        if (input == aChar || input == eChar || input == iChar || input == oChar || input == uChar) {
            return true;
        } else {
            return false;

        }
    }
}
