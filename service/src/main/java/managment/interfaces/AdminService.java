package managment.interfaces;

import DTO.CourseDTO;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AdminService {

    Course createCourse(String desc, String hours);

    void deleteCourse(int id)
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException;

    void updateCourse(String desc, String hours);

    List<Course> listAllCourses();

    List<CourseDTO> listOfAllCourses();

    Teacher createTeacher(String surname, String name);

    void deleteTeacher(int id);

    void updateTeacher(String surname, String name);

    List<Teacher> listAllTeachers();

    Teacher findTeacher(int id);

    void cancelEnrollTeacher(Teacher teacher, Course course);

    void enrollTeacher(Teacher teacher, Course course);

    Mark createMark(Integer mark);

    Course findCourse(int id);
}
