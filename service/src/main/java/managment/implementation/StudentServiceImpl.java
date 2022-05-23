package managment.implementation;

import courses.dao.EntityDaoImplStudent;
import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;
import managment.interfaces.StudentService;

import java.util.List;

/**
 * @author Alex Mikhalevich
 * @version 1.0
 * @created 2022-04-27 12:39
 */
public class StudentServiceImpl implements StudentService {

    private final EntityDaoImplStudent studentDao = new EntityDaoImplStudent();


    @Override
    public Student register(String name, String surname) {
        Student student = Student.builder()
                .name(name)
                .surname(surname)
                .build();
        studentDao.insert(student);
        return student;
    }

    @Override
    public void update(int id, String name, String surname) {
        Student student = Student.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
        studentDao.update(student);
    }

    @Override
    public void deleteById(int id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentDao.select();
    }

    @Override
    public Student findStudentById(int id) {
        return studentDao.getEntity(id);
    }

    @Override
    public void enrollInCourse(Student student, Course course) {
        student.getCourses().add(course);
        studentDao.update(student);
    }

    @Override
    public void cancelEnrollment(Student student, Course course) {
        student.getCourses().remove(course);
        studentDao.update(student);
    }


    @Override
    public Task searchInTasks(int id) {
        return studentDao.searchInTasks(id);
    }


}
