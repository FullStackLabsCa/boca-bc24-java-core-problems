package problems.oldProblems;

import java.util.Scanner;

public class P7_Reversed {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter String");
        String input = scanner.nextLine();
        String reversed = "";


        for (int i = input.length() - 1; i >= 0; i--) {
            reversed = reversed + input.charAt(i);
        }

        System.out.println("Reversed string: " + reversed);

        scanner.close();
    }

}


/*
The `scanner.close();` statement is used to close the `Scanner` object and release any resources associated with it. While it's not strictly necessary for every program, it's a good practice to close a `Scanner` when you're done using it, especially if it's linked to a resource like a file or a network connection.

        ### Reasons for Closing `Scanner`:

        1. **Resource Management**:
        - The `Scanner` class can be used to read input from various sources, like files, network sockets, or the console. When you create a `Scanner`, it may hold onto resources such as file handles or input streams.
        - Closing the `Scanner` releases these resources, which can be important in longer-running programs or when working with limited system resources.

2. **Preventing Memory Leaks**:
        - If a `Scanner` is not closed after use, it could potentially lead to memory leaks or other resource-related issues, especially in programs that open and use many `Scanner` objects.

3. **Good Programming Practice**:
        - Closing resources after use is a good habit, contributing to cleaner, more reliable code. It ensures that your program doesn't accidentally hold onto resources longer than necessary.

        4. **Avoiding Warnings**:
        - Some development environments or static analysis tools may issue warnings if they detect that a `Scanner` (or other resource) is not closed. Closing it helps keep the code warning-free.

### Example:

If you're reading input from a file using a `Scanner`, closing it is crucial:

        ```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
        try {
            File file = new File("example.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

            // Close the Scanner when done
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

In this example:
        - **If you don't close the `Scanner`**: The file may remain open, leading to potential issues if you try to open it elsewhere in your program or system.

        ### Console Input (`System.in`):
        - When using `Scanner` with `System.in` (keyboard input), closing the `Scanner` also closes the underlying `System.in` stream. If you close `System.in`, you won't be able to read from the keyboard input again in the same program. This is why some developers avoid closing the `Scanner` when it's tied to `System.in`, especially in short, simple programs.

        ### Summary:
        - **Always close the `Scanner`** when it’s linked to files or other resources to free up those resources.
        - **For `System.in`**, closing it is optional, but be aware that it will prevent further keyboard input in the program.*/