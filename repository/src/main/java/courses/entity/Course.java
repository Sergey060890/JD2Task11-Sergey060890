package courses.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class Course
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "hours")
    private String hours;

    /**
     * Connection with table "Student"
     */
    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    @Builder.Default
    private Set<Student> students = new HashSet<>();

    /**
     * Connection with table "Teacher"
     */
    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    private Set<Teacher> teachers = new HashSet<>();

    /**
     * Connection with table "Task"
     */
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return description;
    }
}
