package collectionproblem;

public class BracketMatchWithoutStack {
    public static void main(String[] args) {
        String expression = "{[()]}";
        if(isMatching((expression))){
            System.out.println("the expression is matching and in order");
        }else{
            System.out.println("the expression is not matching");
        }

    }
     public static boolean isMatching(String expression){
        int parentheseC =0;
        int squareC=0;
        int curlyBC=0;
        for (char ch : expression.toCharArray()){
            switch (ch){
                case'(':
                    parentheseC++;
                    break;
                case')':
                    parentheseC--;
                    if(parentheseC<0) return false;
                    break;
                case'[':
                    squareC++;
                    break;
                case']':
                    squareC--;
                    if(squareC<0) return false;
                    break;
                case'{':
                    curlyBC++;
                    break;
                case'}':
                    curlyBC--;
                    if(curlyBC<0) return false;
                    break;
                default:
                    break;


            }
        }
        return parentheseC==0 && squareC==0 && curlyBC==0;
     }
}
