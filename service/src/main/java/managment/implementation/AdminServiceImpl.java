package managment.implementation;

import DTO.CourseDTO;
import courses.dao.*;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Teacher;
import managment.interfaces.AdminService;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final EntityDaoImplAdmin daoImplAdmin;

    private final EntityDaoImplCourse daoImplCourse
            = new EntityDaoImplCourse();

    private final EntityDaoImplTeacher daoImplTeacher
            = new EntityDaoImplTeacher();

    private final EntityDaoImplMark daoImplMark
            = new EntityDaoImplMark();

    EntityDaoImplPerson daoPerson
            = new EntityDaoImplPerson();

    public AdminServiceImpl(EntityDaoImplAdmin daoImplAdmin) {
        this.daoImplAdmin = daoImplAdmin;
    }

    @Override
    public Course createCourse(String desc, String hours) {
        Course course = Course.builder()
                .description(desc)
                .hours(hours)
                .build();
        daoImplCourse.insert(course);
        return course;
    }

    @Override
    public void deleteCourse(int id) {
        daoImplCourse.deleteById(id);
    }

    @Override
    public void updateCourse(String desc, String hours) {
        daoImplCourse.update(createCourse(desc, hours));
    }

    @Override
    public List<Course> listAllCourses() {
        return daoImplCourse.select();
    }

    public Course findCourse(int id) {
        return daoImplCourse.getEntity(id);
    }

    public Teacher findTeacher(int id) {
        return daoImplTeacher.getEntity(id);
    }

    @Override
    public Teacher createTeacher(String surname, String name) {
        Teacher teacher = Teacher.builder()
                .name(name)
                .surname(surname)
                .build();
        daoImplTeacher.insert(teacher);
        return teacher;
    }

    @Override
    public void deleteTeacher(int id) {
        daoImplTeacher.deleteById(id);
    }

    @Override
    public void updateTeacher(String surname, String name) {
        daoImplTeacher.update(createTeacher(surname, name));
    }

    @Override
    public List<Teacher> listAllTeachers() {
        return daoImplTeacher.select();
    }

    @Override
    public void enrollTeacher(Teacher teacher, Course course) {
        teacher.getCourses().add(course);
        daoImplTeacher.enrollTeacher(teacher);

    }
    @Override
    public void cancelEnrollTeacher(Teacher teacher, Course course) {
        teacher.getCourses().remove(course);
        daoImplTeacher.enrollTeacher(teacher);

    }

    @Override
    public Mark createMark(Integer grade) {
        Mark mark = Mark.builder()
                .mark(grade)
                .build();
        daoImplMark.insert(mark);
        return mark;
    }

    @Override
    public List<CourseDTO> listOfAllCourses() {
        List<Object[]> lists = daoImplCourse.listOfAllCourse();
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
