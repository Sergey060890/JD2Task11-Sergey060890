package managment.interfaces;

import courses.entity.Course;

import java.util.List;

public interface CourseService {

    Course register(String description, String hours);

    void update(int id, String description, String hours);

    void deleteById(int id);

    List<Course> findAll();
}
