package managment.interfaces;

import DTO.TaskDTO;
import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;

import java.util.List;

public interface TaskService {

    Task add(String description);

    void update(int id, String description, String review);

    void deleteById(int id);

    Task findTaskById(int id);

    void assignToCourse(Task task, Course course);

    void cancelAssignmentToCourse(Task task, Course course);

    void assignToStudent(Task task, Student student);

    void cancelAssignmentToStudent(Task task, Student student);

    public List<TaskDTO> listOfAllTasks();
}
