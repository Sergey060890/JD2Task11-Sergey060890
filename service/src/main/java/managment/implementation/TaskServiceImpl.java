package managment.implementation;

import DTO.TaskDTO;
import courses.dao.EntityDaoImplTask;
import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;
import liquibase.pro.packaged.C;
import managment.interfaces.TaskService;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final EntityDaoImplTask taskDao = new EntityDaoImplTask();

    @Override
    public Task add(String description) {
        Task task = Task.builder()
                .description(description)
                .build();
        taskDao.insert(task);
        return task;
    }

    @Override
    public void update(int id, String description,  String review) {
        Task task = Task.builder()
                .id(id)
                .description(description)

                .review(review)
                .build();
        taskDao.update(task);
    }

    @Override
    public void deleteById(int id) {
        taskDao.deleteById(id);
    }

    @Override
    public Task findTaskById(int id) {
        return taskDao.getEntity(id);
    }

    @Override
    public void assignToCourse(Task task, Course course){
        task.setCourse(course);
        taskDao.update(task);
    }

    public void cancelAssignmentToCourse(Task task, Course course){
        task.setCourse(null);
        taskDao.update(task);
    }

    @Override
    public void assignToStudent(Task task, Student student){
        task.setStudent(student);
        taskDao.update(task);
    }

    public void cancelAssignmentToStudent(Task task, Student student){
        task.setStudent(null);
        taskDao.update(task);
    }

    @Override
    public List<TaskDTO> listOfAllTasks() {
        List<Object[]> lists = taskDao.listOfAllTask();
        List<TaskDTO> listOfTask = new ArrayList<>();
        for (Object[] list : lists) {
            listOfTask.add(TaskDTO.builder()
                    .id((Integer) list[0])
                    .description((String) list[1])
                    .review((String) list[2])
                    .course((String) list[3])
                    .build());
        }
        return listOfTask;

    }
}
