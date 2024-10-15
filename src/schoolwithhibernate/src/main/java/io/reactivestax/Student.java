package io.reactivestax;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "student")

//One student can be enrolled in multiple courses;
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    Set<Course> course = new HashSet<>();

    List<Course> courses = new ArrayList<>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments = new HashSet<>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Grades> grades = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "enrollment", joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();
}
