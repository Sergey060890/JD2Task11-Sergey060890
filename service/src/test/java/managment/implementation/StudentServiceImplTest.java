package managment.implementation;

import courses.dao.EntityDaoImplAdmin;
import courses.dao.EntityDaoImplTeacher;
import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;
import junit.framework.TestCase;
import managment.interfaces.AdminService;
import managment.interfaces.StudentService;
import managment.interfaces.TeacherService;

import static managment.implementation.ConstantsForTest.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-13 19:37
 */
public class StudentServiceImplTest extends TestCase {


    public void testRegister() {
        StudentService studentService = new StudentServiceImpl();
        studentService.register(STUDENT_NAME, STUDENT_SURNAME);

    }

    public void testEnrollInCourse() {
        Course course = UtilsForTest.getCourse1();
        StudentService studentService = new StudentServiceImpl();
     //   studentService.enrollInCourse(studentService.register(STUDENT_NAME, STUDENT_SURNAME), course);
    }

    public void testSearchInTasks() {
        AdminService adminService = new AdminServiceImpl(new EntityDaoImplAdmin());
        TeacherService teacherService = new TeacherServiceImpl(new EntityDaoImplTeacher());
        StudentService studentService = new StudentServiceImpl();
        teacherService.setTaskToStudent(studentService.register(STUDENT_NAME, STUDENT_SURNAME),
                teacherService.addTask(TASK_DESC_1, adminService.createCourse(DESCRIPTION_COURSE_1, HOURS_COURSE)));
        System.out.println(studentService.searchInTasks(1));
    }
}