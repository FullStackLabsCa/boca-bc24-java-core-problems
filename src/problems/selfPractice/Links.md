DOCKER and DataBase
```declarative
brew install --cask docker
```
if you dont have brew then type this
```declarative

/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

docker run --name mysql -e MYSQL_ROOT_PASSWORD=password123  -p 3306:3306  bitnami/mysql:latest
```

Qracle SQL Developer Download:
https://www.oracle.com/database/sqldeveloper/technologies/download/

```declarative
docker run --name mysql -e MYSQL_ROOT_PASSWORD=password123  -p 3306:3306  bitnami/mysql:latest
```
https://dev.mysql.com/downloads/connector/j/

```declarative
mysql -u root -p -h 127.0.0.1 -P 3306
```

```declarative
Use  bootcamp;
```
```declarative
CREATE TABLE Courses (
course_id INT AUTO_INCREMENT PRIMARY KEY,
course_name VARCHAR(100) UNIQUE NOT NULL
);
```
```
CREATE TABLE Students (
student_id INT AUTO_INCREMENT PRIMARY KEY,
student_name VARCHAR(100) NOT NULL
);
```
```
CREATE TABLE Enrollments (
enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
course_id INT NOT NULL,
student_id INT NOT NULL,
FOREIGN KEY (course_id) REFERENCES Courses(course_id),
FOREIGN KEY (student_id) REFERENCES Students(student_id)
);
```
```
CREATE TABLE Grades (
grade_id INT AUTO_INCREMENT PRIMARY KEY,
course_id INT NOT NULL,
student_id INT NOT NULL,
grade DECIMAL(5, 2) NOT NULL,
FOREIGN KEY (course_id) REFERENCES Courses(course_id),
FOREIGN KEY (student_id) REFERENCES Students(student_id)
);
```
```
select * from Courses;
select * from Students;
select * from Enrollments;
select * from Grades;
```

SQL Practice with database: https://www.sql-practice.com/

Also follow this link to setup your COOL iTerm terminal with important plugins. Follow all the way to the end. and let us know if you need help anywhere..   we will be spending more time on Terminal .. so this link will bring it on Steroids...: https://www.freecodecamp.org/news/jazz-up-your-zsh-terminal-in-seven-steps-a-visual-guide-e81a8fd59a38


