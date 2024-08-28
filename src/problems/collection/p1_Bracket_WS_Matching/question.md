### **Problem Statement: Bracket Matching without Using a Stack**

#### **Objective:**

Design and implement a Java program that checks whether an expression containing various types of brackets (parentheses `()`, square brackets `[]`, and curly braces `{}`) is properly balanced. The challenge is to accomplish this without using a stack data structure.

#### **Requirements:**

1. **Bracket Types:**
- The expression will contain three types of brackets: parentheses `()`, square brackets `[]`, and curly braces `{}`.

2. **Balanced Expression Criteria:**
- A balanced expression is one in which every opening bracket has a corresponding closing bracket of the same type, and the brackets are correctly nested. For example, `{[()]}` is balanced, while `{[(])}` is not.

3. **Input:**
- The program should accept a string consisting of only these three types of brackets.

4. **Output:**
- The program should return `true` if the input string is balanced according to the standard rules, and `false` otherwise.

5. **Constraints:**
- **No Stack**: The solution must be implemented without using a stack. Instead, consider alternative approaches, such as using counters.

6. **Core Functionalities:**
- **Bracket Matching Logic**: Implement logic to match opening and closing brackets without relying on a stack. You may use counters for each type of bracket or simulate the stack behavior using other methods.
- **Correct Nesting**: Ensure that the solution can correctly handle nested brackets, not just counting them but ensuring they are properly ordered.
- **Edge Cases**: Handle edge cases such as an empty string, single bracket, and mismatched brackets correctly.

7. **Examples:**
- Input: `{[()]}`, Output: `true` (balanced)
- Input: `{[(])}`, Output: `false` (not balanced)
- Input: `[]`, Output: `true` (balanced)
- Input: `{[}`, Output: `false` (not balanced)

8. **Scenario Example:**

Imagine you are tasked with developing a simple syntax checker for a custom configuration language where brackets are used to group settings. Due to certain constraints, you are not allowed to use a stack to validate the grouping. You need to ensure that all groupings are correctly nested and balanced using an alternative method.

9. **Outcome:**

Students should be able to:

- Implement a solution to the bracket matching problem without relying on a stack.
- Explore alternative data structures or algorithms to achieve the same goal.
- Ensure the solution correctly identifies balanced and unbalanced bracket sequences and handles various edge cases effectively.
