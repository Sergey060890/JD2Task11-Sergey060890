package managment.implementation;

import DTO.CourseDTO;
import courses.dao.EntityDaoImplTask;
import courses.dao.EntityDaoImplTeacher;
import courses.entity.*;
import managment.interfaces.TeacherService;

import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final EntityDaoImplTeacher daoImplTeacher;

    private final EntityDaoImplTask daoImplTask =
            new EntityDaoImplTask();

    public TeacherServiceImpl(EntityDaoImplTeacher daoImplTeacher) {
        this.daoImplTeacher = daoImplTeacher;
    }

    @Override
    public Task addTask(String desc, Course course) {
        Task task = Task.builder()
                .description(desc)
                .course(course)
                .build();
        daoImplTask.insert(task);
        return task;
    }

    @Override
    public void deleteTask(Integer id) {
        daoImplTask.deleteById(id);
    }

    @Override
    public void rateTask(Integer id, Mark mark, String review) {
        Task task = getTask(id);
        task.setMark(mark);
        task.setReview(review);
        daoImplTask.update(task);
    }

    @Override
    public void showTask() {
        daoImplTask.select();
    }

    @Override
    public void setTaskToStudent(Student student, Task task) {
        task.setStudent(student);
        daoImplTask.update(task);
    }

    @Override
    public Task getTask(Integer id) {
        return daoImplTask.getEntity(id);
    }

    @Override
    public Teacher register(String name, String surname) {
        Teacher teacher = Teacher.builder()
                .name(name)
                .surname(surname)
                .build();
        daoImplTeacher.insert(teacher);
        return teacher;
    }

    @Override
    public void update(int id, String name, String surname) {
        Student student = Student.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
        daoImplTeacher.update(student);
    }

    @Override
    public void deleteById(int id) {
        daoImplTeacher.deleteById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return daoImplTeacher.select();
    }

    @Override
    public void showTaskWithMark() {
        daoImplTask.showTaskWithMark();
    }

    @Override
    public void showTaskWithoutMark() {
        daoImplTask.showTaskWithoutMark();
    }

    @Override
    public List<CourseDTO> findCoursesForTeacher(String teacherSurname) {
        List<Object[]> lists = daoImplTeacher.findCoursesForTeacher(teacherSurname);
        List<CourseDTO> listOfCourseDto = new ArrayList<>();
        for (Object[] list : lists) {
            listOfCourseDto.add(CourseDTO.builder()
                    .id((Integer) list[0])
                    .description((String) list[1])
                    .hours((String) list[2])
                    .teacherSurname((String) list[3])
                    .teacherName((String) list[4])
                    .build());
        }
        return listOfCourseDto;
    }

}
