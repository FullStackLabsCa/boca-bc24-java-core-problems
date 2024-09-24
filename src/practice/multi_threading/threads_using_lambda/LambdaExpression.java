package practice.multi_threading.threads_using_lambda;

public class LambdaExpression {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Hello World"));
        t1.start();
    }
}
