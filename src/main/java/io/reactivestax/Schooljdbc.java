package io.reactivestax;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Schooljdbc {

    // Add course
    public void addCourse(String courseName) {

        SessionFactory factory = io.reactivestax.HibernateUtilClass.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Course course = new Course();
        course.setName(courseName);
        session.save(course);
        transaction.commit();
        System.out.println("Courses added successfully");

    }

    public void enrollStudent(int studentId, String studentName, String courseName) {

        SessionFactory factory = io.reactivestax.HibernateUtilClass.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();


        try {
            Course course = (Course) session.createQuery("FROM Course WHERE name = :name").setParameter("name", courseName).uniqueResult();
            if (course == null) {
                course = new Course();
                course.setName(courseName);
                session.save(course);
            }

            io.reactivestax.Student student = (io.reactivestax.Student) session.createQuery("FROM Student WHERE name = :name").setParameter("name", studentName).uniqueResult();
            if (student == null) {
                student = new io.reactivestax.Student();
                student.setName(studentName);
                session.save(student);
            }

            io.reactivestax.Enrollment enrollment = (io.reactivestax.Enrollment) session.createQuery("FROM Enrollment where student.id = :Id and course.id = :Id").setParameter("Id", student.getId()).setParameter("Id", course.getCourseId()).uniqueResult();
            if (enrollment == null) {
                enrollment = new io.reactivestax.Enrollment();
                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollment.setId(studentId);
                session.save(enrollment);
            }

            transaction.commit();
        } finally {

        }

    }

    //    // Assign grade
    public void assignGrade(int studentId, String courseName, double gradeValue) {
        SessionFactory factory = io.reactivestax.HibernateUtilClass.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Course course = (Course) session.createQuery("FROM Course WHERE name = :name").setParameter("name", courseName).uniqueResult();
            if (course == null) {
                System.out.println("The course does not exist");
            } else {
                session.save(course);
            }

            io.reactivestax.Student student = (io.reactivestax.Student) session.createQuery("FROM Student WHERE id = :Id").setParameter("Id", studentId).uniqueResult();
            if (student == null) {
                //      student = new Student();
                System.out.println("Student does not exist");
            } else {
                session.save(student);
            }


            io.reactivestax.Enrollment enrollment = (io.reactivestax.Enrollment) session.createQuery("FROM Enrollment where id = :Id and id = :Id").setParameter("Id", student.getId()).setParameter("Id", course.getCourseId()).uniqueResult();
            if (enrollment == null) {
                System.out.println("Can't enroll in this course");
            } else {
                session.save(enrollment);
            }

            io.reactivestax.Grades grade = new io.reactivestax.Grades();
            grade.setCourse(course);
            grade.setStudent(student);
            grade.setGradeValue(gradeValue);
            session.save(grade);
            transaction.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

