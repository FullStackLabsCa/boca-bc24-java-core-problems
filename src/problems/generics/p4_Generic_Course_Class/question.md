### **Problem 4: Generic Course Class**

#### **Objective:**
Design and implement a generic class `Course` that can store and manage information about a course, including the students enrolled and their corresponding grades. The `Course` class should be versatile, allowing it to work with any type of student identifier and any numeric type for grades, such as `Integer`, `Double`, etc.

#### **Requirements:**

1. **Generic Design:**
    - The `Course` class should use generics to handle different types of student identifiers (e.g., `String` for names, `Integer` for student IDs) and numeric grades (e.g., `Integer`, `Double`).
    - The class should ensure type safety by working with these types and performing necessary operations without relying on specific implementations.

2. **Core Functionalities:**
    - **Enrolling Students**: The class should allow enrolling students in the course. Each student should be uniquely identified by an identifier of type `S`.
    - **Assigning Grades**: The class should provide functionality to assign a grade of type `G` to a specific student.
    - **Retrieving Grades**: The class should allow retrieval of a student's grade based on their identifier.
    - **Listing All Grades**: The class should offer a way to retrieve a collection of all students and their corresponding grades.

3. **Edge Cases Handling:**
    - The class should handle cases where a grade is requested for a student who has not been assigned a grade yet or who is not enrolled in the course.
    - It should ensure that grades can be updated if necessary and reflect the latest information for each student.

4. **Ease of Use:**
    - The class should provide an intuitive interface for adding students, assigning grades, and retrieving information.
    - It should be easily adaptable to different educational contexts, whether used in a simple classroom setting or a more complex educational system.

5. **Scalability:**
    - The design should accommodate large numbers of students and efficiently manage the storage and retrieval of their grades.
    - It should maintain the integrity of the data and perform operations in a way that scales well with the number of students.

#### **Scenario Example:**

Imagine a scenario where a teacher is managing a course and needs to keep track of the students enrolled and their grades. Each student can be identified by a unique identifier (such as a name or a student ID), and their grades could be in various formats, such as percentages or GPA scores. The teacher needs to be able to enroll students, assign grades, and retrieve grades as needed, ensuring that the course's data remains accurate and up-to-date.

By implementing the `Course` class with generics, the teacher can manage these tasks seamlessly, regardless of the type of student identifier or grade format, making the class highly adaptable to various educational needs.

#### **Outcome:**

Students should be able to design the `Course` class that can:

- Store and manage a collection of students and their corresponding grades.

- Provide methods for enrolling students, assigning and updating grades, and retrieving grades.

- Handle various types of student identifiers and numeric grades through the use of generics.

- Offer a user-friendly interface suitable for managing a course in different educational contexts.


