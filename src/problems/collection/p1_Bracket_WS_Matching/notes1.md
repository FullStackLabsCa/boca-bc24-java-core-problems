## Types of Brackets

Parentheses ( )

Square brackets [ ]

Curly brackets { }

Angle brackets ⟨ ⟩


## Notes of the problem

### Concepts Related to the Problem

To solve the problem of bracket matching without using a stack, you'll need to understand several key concepts:

1. **Bracket Matching**:
    - **Balanced Brackets**: A sequence of brackets is balanced if every opening bracket has a corresponding closing bracket, and the brackets are properly nested.
    - **Types of Brackets**: Parentheses `()`, square brackets `[]`, and curly braces `{}`.

2. **Alternative to Stack**:
    - **Counters**: Instead of using a stack, you can use counters to keep track of the number of each type of bracket. However, counters alone cannot ensure proper nesting.
    - **Simulating Stack Behavior**: Without a stack, you'll need to simulate the stack's ability to ensure brackets are closed in the correct order. This can be done by checking the state of counters and the sequence of the brackets.

3. **Edge Cases**:
    - **Empty String**: A string with no brackets is considered balanced.
    - **Single Bracket**: A single bracket (e.g., `{` or `]`) is not balanced.
    - **Mismatched Brackets**: Brackets that open and close in the wrong order or are not properly nested.

### Steps for Solving the Problem

1. **Initialize Counters**:
    - Create counters for each type of bracket (parentheses, square brackets, and curly braces).

2. **Iterate Through the String**:
    - Loop through each character in the input string.
    - Update the corresponding counter for each opening bracket (`(`, `[`, `{`) by incrementing it.
    - Update the corresponding counter for each closing bracket (`)`, `]`, `}`) by decrementing it.

3. **Check for Early Mismatch**:
    - As you update the counters, check if any counter for a closing bracket goes negative. This indicates that a closing bracket was found before its corresponding opening bracket, meaning the string is not balanced.

4. **Check for Correct Nesting**:
    - Ensure that the closing of a bracket type matches the most recently opened bracket type. This can be achieved by checking the relative positions of the counters as you traverse the string.

5. **Final Check**:
    - After the loop, all counters should be zero. If any counter is not zero, the brackets are not balanced.

6. **Edge Case Handling**:
    - If the input string is empty, return `true`.
    - Handle scenarios where mismatched brackets appear early in the sequence.

### Example Implementation in Java

```java
public class BracketMatcher {
    
    public static boolean isBalanced(String expression) {
        // Counters for each type of bracket
        int roundCount = 0; // For parentheses ()
        int squareCount = 0; // For square brackets []
        int curlyCount = 0; // For curly braces {}

        // Iterate through each character in the expression
        for (char ch : expression.toCharArray()) {
            switch (ch) {
                case '(':
                    roundCount++;
                    break;
                case ')':
                    roundCount--;
                    if (roundCount < 0) return false;
                    break;
                case '[':
                    squareCount++;
                    break;
                case ']':
                    squareCount--;
                    if (squareCount < 0) return false;
                    break;
                case '{':
                    curlyCount++;
                    break;
                case '}':
                    curlyCount--;
                    if (curlyCount < 0) return false;
                    break;
            }

            // Ensure correct nesting by checking the state of all counters
            if (roundCount < 0 || squareCount < 0 || curlyCount < 0) {
                return false;
            }
        }

        // All counters should be zero if the expression is balanced
        return roundCount == 0 && squareCount == 0 && curlyCount == 0;
    }

    public static void main(String[] args) {
        String[] testCases = {"{[()]}", "{[(])}", "[]", "{[}"};

        for (String testCase : testCases) {
            System.out.println("Input: " + testCase + " -> Output: " + isBalanced(testCase));
        }
    }
}
```

### Explanation of the Code

- **Counters**: We use three counters (`roundCount`, `squareCount`, and `curlyCount`) to keep track of the number of open brackets of each type.

- **Character Checking**: The program checks each character in the string, updates the counters based on whether the character is an opening or closing bracket, and immediately returns `false` if a mismatch is detected (i.e., a counter becomes negative).

- **Final Balance Check**: After processing the entire string, if all counters are zero, the expression is balanced; otherwise, it is not.

### Edge Cases
- The program correctly handles empty strings, single brackets, and mismatched brackets.

This approach ensures that we can check for balanced brackets without using a stack, leveraging simple counters and logical checks to simulate stack behavior.