package problems;

public class AddWithoutAdd {

    public int add(int a, int b){

        while(b != 0){
            a = a + 1;
            b--;
        }

        return a;
    }

    public static void main(String[] args) {

        AddWithoutAdd addition = new AddWithoutAdd();
        System.out.println(addition.add(4,0));;
    }
}
