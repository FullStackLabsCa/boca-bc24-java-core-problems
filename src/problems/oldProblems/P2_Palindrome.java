/*
Problem 2: Palindrome Check
Objective
Write a Java program to check if a given string is a palindrome.
A palindrome is a string that reads the same forward and backward, ignoring spaces, punctuation, and case differences.

Steps to Implement Palindrome Check
    Input the string: Take a string as input from the user.
    Normalize the string: Remove non-alphanumeric characters and convert it to lower case.
    Check for palindrome:
    Compare characters from the beginning and end of the string moving towards the center.
    If all corresponding characters match, the string is a palindrome.
    Output the result: Print whether the string is a palindrome or not.
*/

/*
Brain Storming : Steps to Implement Palindrome Check:

        (i.) Input the String: take input from the user. This can be done using Scanner.
        (ii.) Normalize the String: Convert the string to lowercase to ensure the comparison is case-insensitive.
                    Remove all non-alphanumeric characters (like spaces, punctuation) using regular expressions.
        (iii.) Check for Palindrome: Use a loop to compare characters from the start and end of the string moving towards the center.
                    If at any point the characters don't match, the string is not a palindrome.
        (iv.) Output the Result: Print the result indicating whether the string is a palindrome or not.*/

package problems.oldProblems;

import java.util.Scanner;

public class P2_Palindrome {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;

        // Step 1: Input the string

        while (true) {
            System.out.println("Enter string to check the string is palindrome or not: ");
            input = scanner.nextLine().trim(); // Trim leading/trailing spaces

            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Did not get input. Please enter valid string");
        }

        // Step 2: Normalize the string
        String ns = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
//        System.out.println(ns);

        // Step 3: Check for Palindrome
        boolean isPalindrome = true;
        int length = ns.length();

        for (int i = 0; i < length / 2; i++) {
            if (ns.charAt(i) != ns.charAt(length - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        // Step 4: Output
        if (isPalindrome) {
            System.out.println("Congratulation! The string " + ns + " is Palindrome");
        } else {
            System.out.println("The string " + ns + " is not Palindrome");
        }

        scanner.close();

    }


}






/*

    Using `while (true)` and `try-catch` serve different purposes in programming. Let’s break down why I used `while (true)` in this context and compare it with using `try-catch`.

### 1. **Purpose of `while (true)` in this Context**:
   - **Looping Until Valid Input**:
     - The goal is to keep prompting the user until they provide a valid input (i.e., a non-empty string). `while (true)` creates an infinite loop, which continues running until a `break` statement is encountered. The loop breaks when valid input is detected.
     - This is a common pattern when you want to keep trying something until it succeeds, such as getting valid input from a user.

   - **When to Use `while (true)`**:
     - When you need a loop to run indefinitely until a specific condition is met.
     - It’s simple and straightforward for cases where the exit condition is clear and usually not based on exceptions but on logical checks.

### 2. **Purpose of `try-catch`**:
   - **Exception Handling**:
     - `try-catch` is used to handle exceptions—unexpected events that occur during the execution of a program. For example, if you're reading from a file and the file doesn't exist, an `IOException` might be thrown, which you would catch and handle.
     - It’s not meant to control the flow of a loop based on normal input validation like checking if a string is empty.

   - **When to Use `try-catch`**:
     - When you expect that an operation might fail due to reasons outside of your control (e.g., network issues, invalid file paths, or parsing errors).
     - It’s useful for handling errors gracefully and ensuring that your program can recover or fail elegantly when something goes wrong.

### Why `while (true)` Instead of `try-catch` Here?

In the case of checking if a user has entered a valid string:

- **Normal Input Validation**: The scenario where a user might hit enter without typing anything is not an exceptional event; it’s a normal case that can be handled with basic logic. There’s no need to throw or catch an exception, which is why `while (true)` is appropriate here.

- **Simplicity and Readability**: Using `while (true)` with a `break` condition makes the code easier to understand and maintain for this specific task. It’s clear that the loop continues until a valid string is entered.

### What If We Used `try-catch`?

If we were to use `try-catch`, it would look something like this:

```java
import java.util.Scanner;

public class PalindromeCheck {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = null;

        while (input == null || input.isEmpty()) {
            System.out.println("Enter a string to check if it's a palindrome:");
            try {
                input = scanner.nextLine().trim();  // Trim leading/trailing spaces
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Step 2: Normalize the string
        String normalizedString = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Step 3: Check for palindrome
        boolean isPalindrome = true;
        int length = normalizedString.length();

        for (int i = 0; i < length / 2; i++) {
            if (normalizedString.charAt(i) != normalizedString.charAt(length - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        // Step 4: Output the result
        if (isPalindrome) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }

        scanner.close();
    }
}
```

In this case, we’re catching an `IllegalArgumentException` if the input is empty. While this works, it’s not necessary to treat an empty string as an exceptional case that requires exception handling. The logic with `while (true)` is more appropriate and straightforward for this scenario.

### Conclusion:

- **`while (true)`** is better suited for input validation loops where you want to keep prompting the user until they provide valid input.
- **`try-catch`** is for handling actual exceptions that occur due to unexpected conditions, not for managing normal input validation.
*/
