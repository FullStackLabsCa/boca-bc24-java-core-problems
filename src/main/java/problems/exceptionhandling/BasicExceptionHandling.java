package problems.exceptionhandling;

public class BasicExceptionHandling {
    public static void main(String[] args) {
        try {
            // Code that might throw an exception
            int[] numbers = new int[2];
            // This line will throw an ArrayIndexOutOfBoundsException
            numbers[3] = 5;
        } catch (IndexOutOfBoundsException e) {
            //This block handles the exception
            System.out.println("An exception occurred: " + e.getMessage());
        } catch (NullPointerException nullPointerException) {
            System.out.println("NullPointerException,,,");
        } finally {
            // This block always executes
            System.out.println("This will always execute, regardless of whether an exception was thrown or not.");
        }
    }
}


