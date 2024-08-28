#### **Problem 3: Generic GradeBook Class**

#### **Objective:**
Design and implement a generic class `GradeBook` that is capable of storing and managing the grades of students. The `GradeBook` class should be versatile, allowing it to work with any numeric type, such as `Integer`, `Double`, `Float`, etc. This class will provide essential functionalities like adding grades, calculating the average grade, and determining the highest and lowest grades.

#### **Requirements:**

1. **Generic Design:**
    - The `GradeBook` class should be designed using generics to handle any numeric type that extends the `Number` class (e.g., `Integer`, `Double`).
    - The class should ensure type safety by working with these numeric types and performing necessary calculations without relying on specific numeric classes.

2. **Core Functionalities:**
    - **Adding Grades**: The class should support adding individual grades of type `T` to the grade book.
    - **Calculating the Average**: The class should provide a method to calculate and return the average of all the grades stored in the grade book. This average should be returned as a `double` value.
    - **Finding the Highest Grade**: The class should offer a method to find and return the highest grade in the grade book.
    - **Finding the Lowest Grade**: Similarly, the class should provide a method to find and return the lowest grade in the grade book.

3. **Edge Cases Handling:**
    - The class should handle scenarios where no grades have been added (e.g., when calculating the average or finding the highest/lowest grade).
    - It should also be designed to handle cases where grades are added or removed, ensuring that the methods reflect the current state of the grade book.

4. **Ease of Use:**
    - The class should be easy to use for a variety of numeric types without requiring users to implement specific logic for different types.
    - It should provide clear and intuitive methods that can be easily integrated into broader applications, such as student management systems or educational software.

5. **Scalability:**
    - The design should allow the `GradeBook` class to scale well with a large number of grades, efficiently performing calculations and maintaining the integrity of the stored data.

#### **Scenario Example:**

Imagine a scenario where a teacher needs to manage the grades for a class of students. The grades could be in different formats, such as percentages (e.g., 85%, 90%) or GPA scores (e.g., 3.5, 4.0). The teacher wants to store these grades in a `GradeBook` and perform various operations, such as finding the class average, identifying the top-performing student, and recognizing students who might need extra help based on the lowest grades.

By implementing the `GradeBook` class with generics, the teacher can easily manage these grades regardless of their numeric type, ensuring that the class can be used in diverse educational contexts.

#### **Outcome:**

Students should be able to design the `GradeBook` class that can:

- Store grades of a generic numeric type.
- Perform operations like calculating averages and finding the highest and lowest grades.
- Handle edge cases appropriately.
- Offer a user-friendly interface for managing student grades in various educational applications.


