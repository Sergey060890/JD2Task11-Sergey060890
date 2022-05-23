package managment.interfaces;

import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @version 1.0
 * @created 2022-04-27 21:57
 */
public interface StudentService {

    Student register(String name, String surname);

    void update(int id, String name, String surname);

    void deleteById(int id);

    List<Student> findAll();

    Student findStudentById(int id);

    void enrollInCourse(Student student, Course course);

    void cancelEnrollment(Student student, Course course);

    Task searchInTasks(int id);

}
