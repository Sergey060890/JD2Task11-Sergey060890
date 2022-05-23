package managment.interfaces;

import DTO.CourseDTO;
import courses.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface TeacherService {
    /**
     * Add Task
     */
    Task addTask(String desc, Course course);

    /**
     * Delete Task
     */
    void deleteTask(Integer id)
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException;

    /**
     * Update Task
     */
     void rateTask(Integer id, Mark mark, String review);

    /**
     * Print Task
     */
    void showTask();

    /**
     * Print Task with Mark
     */
    void showTaskWithMark();

    /**
     * Print Task without Mark
     */
    void showTaskWithoutMark();

    /**
     * Print Task and Student
     */
    void setTaskToStudent(Student student, Task task);

    /**
     * Get Task
     */
    Task getTask(Integer id);

    Teacher register(String name, String surname);

    void update(int id, String name, String surname);

    void deleteById(int id);

    List<Teacher> findAll();
    List<CourseDTO> findCoursesForTeacher(String teacherSurname);

}
