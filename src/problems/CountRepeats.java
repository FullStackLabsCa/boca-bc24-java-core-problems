package problems;

import java.util.ArrayList;
import java.util.List;

class AlphabetInfo{
    char alphabet;
    int repetition = 1;

    public AlphabetInfo(char alphabet) {
        this.alphabet = alphabet;
    }

    public char getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(char alphabet) {
        this.alphabet = alphabet;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }
}

public class CountRepeats {

    public List<AlphabetInfo> countRepeats(String toProcess){

        char seperatedCharacters[] = new char[toProcess.length()];
        List<AlphabetInfo> listOfAlphabets = new ArrayList<>();

        for(int i = 0; i < toProcess.length(); i++){
            seperatedCharacters[i] = toProcess.toLowerCase().charAt(i);
        }

        for (char c : seperatedCharacters){
            boolean alphabetExistence = false;

            for(AlphabetInfo alphabet : listOfAlphabets){
                if(alphabet.getAlphabet() == c) {
//                    System.out.println("Alphabet already Exists. Incrementing Repetition");
                    alphabetExistence = true;
                    alphabet.setRepetition(alphabet.getRepetition() + 1);
                } else {
                    // Do Nothing
                }
            }
            if(alphabetExistence){
//                System.out.println("Alphabet already Exists.");
            } else {
//                System.out.println("Alphabet does not Exist. Adding to the list");
                AlphabetInfo newAlphabet = new AlphabetInfo(c);
                listOfAlphabets.add(newAlphabet);
            }
        }

        return listOfAlphabets;
    }

    public int countRepeatsOf(String toProcess, char character){
        int repetitions = 0;

        List<AlphabetInfo> listOfAlphabets = this.countRepeats(toProcess);

        for (AlphabetInfo alphabet : listOfAlphabets){
            if(alphabet.getAlphabet() == character) {
                repetitions = alphabet.getRepetition();
            }
        }
        return repetitions;
    }

    public static void main(String[] args) {
        CountRepeats testString = new CountRepeats();

//        List<AlphabetInfo> listOfAlphabets = testString.countRepeats("Akshat111####1111");
//        for (AlphabetInfo alphabet : listOfAlphabets){
//            System.out.print(alphabet.getAlphabet() + " ");
//            System.out.print(alphabet.getRepetition() + "\n");
//        }

        System.out.println(testString.countRepeatsOf("Akshat111$$1", '$'));
    }
}
