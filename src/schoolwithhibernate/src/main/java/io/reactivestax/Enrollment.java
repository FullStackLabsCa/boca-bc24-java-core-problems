package io.reactivestax;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "enrollment")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
//Many students enrolled in one class
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // enrollDate
    // courseStartDate
    // courseEndDate


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}

