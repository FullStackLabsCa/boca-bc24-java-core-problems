package io.reactivestax;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "name")
    private String name;

    // String description // Database BLOB

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "course_student", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {
            @JoinColumn(name = "student_id")})
    Set<Student> students = new HashSet<Student>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Grades> grades = new HashSet<>();
}


