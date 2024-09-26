package mathematics.problem;

public class MaxValueArray { ///ArrayListList
    public static void main(String[] args) {
        int [] number = {299,3,56,87,98,4}; // array number
        int maxValue =0; // lets initialzie
        for(int i : number){
            if(i>maxValue){
                maxValue=i;
            }
        }
        System.out.println(maxValue);
    }
}
