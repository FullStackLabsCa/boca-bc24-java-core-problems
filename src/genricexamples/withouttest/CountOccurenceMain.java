package genricexamples.withouttest;

public class CountOccurenceMain {

        public static void main(String[] args) {
            String[] name ={"John","Alex","Nick","Alex","Brat","Lee","Alex"};
            String checkname ="Alex";
            int repeatedString = CountOccurence.countOccurrences(name,checkname);
            System.out.println( checkname + " repeated: "+repeatedString+" times");

            Integer[] numbers ={2,32,23,23,43,22,23,23,6,54,354,23};
            Integer checkNumber= 23;
            Integer repeatedNumber = CountOccurence.countOccurrences(numbers,checkNumber);
            System.out.println(checkNumber +" repeated "+ repeatedNumber +" times");

            Character[] characters ={'r','f','u','f','f'};
            Character character= 'f';
            Integer repeatedchar = CountOccurence.countOccurrences(characters,character);
            System.out.println(character +" repeated "+ repeatedchar +" times");

        }
}
