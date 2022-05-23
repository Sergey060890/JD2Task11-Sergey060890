package managment.implementation;

import courses.dao.EntityDaoImpl;
import courses.dao.EntityDaoImplCourse;
import courses.entity.Course;
import managment.interfaces.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private EntityDaoImpl daoImplCourse = new EntityDaoImplCourse();

    public CourseServiceImpl(EntityDaoImpl daoImplCourse) {
        this.daoImplCourse = daoImplCourse;
    }

    @Override
    public Course register(String description, String hours) {
        Course course = Course.builder()
                .description(description)
                .hours(hours)
                .build();
        daoImplCourse.insert(course);

        return course;
    }

    @Override
    public void update(int id, String description, String hours) {
        Course course = Course.builder()
                .id(id)
                .description(description)
                .hours(hours)
                .build();
        daoImplCourse.update(course);
    }

    @Override
    public void deleteById(int id) {
        daoImplCourse.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return daoImplCourse.select();
    }
}
