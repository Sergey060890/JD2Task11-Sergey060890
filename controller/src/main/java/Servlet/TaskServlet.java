package Servlet;

import DTO.TaskDTO;
import courses.dao.EntityDao;
import courses.dao.EntityDaoImplAdmin;
import courses.dao.EntityDaoImplTask;
import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;
import liquibase.pro.packaged.E;
import managment.implementation.AdminServiceImpl;
import managment.implementation.CourseServiceImpl;
import managment.implementation.StudentServiceImpl;
import managment.implementation.TaskServiceImpl;
import managment.interfaces.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "TaskServlet", value = "/task")
public class TaskServlet extends HttpServlet {

    public static final String DESCRIPTION = "description";
    public static final String REVIEW = "review";
    private TaskServiceImpl taskService = new TaskServiceImpl();

    private AdminService adminService = new AdminServiceImpl(new EntityDaoImplAdmin());

    private EntityDao entityDao = new EntityDaoImplTask();

    private StudentServiceImpl studentService = new StudentServiceImpl();

    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

    public static final String ID_TASK = "id";

    public static final String ID_COURSE = "idCourse";

    public static final String ID_STUDENT = "idStudent";

    public static final String ACTION = "action";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<TaskDTO> taskDTOS = taskService.listOfAllTasks();
        req.setAttribute("tasks", taskDTOS);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/task.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<Course> courseList = adminService.listAllCourses();
        req.setAttribute("courses", courseList);
        List<Student> studentList = studentService.findAll();
        req.setAttribute("students", studentList);
        String action = req.getParameter(ACTION);
        switch (action) {
            case "add":
                saveTask(req, resp);
                break;
            case "delete":
                deleteTask(req, resp);
                break;
            case "update":
                updateTask(req, resp);
                break;
            case "assignToCourse":
                assignTaskToCourse(req, resp);
                break;
            case "assignToStudent":
                assignTaskToStudent(req, resp);
                break;
            case "addTaskToCourse":
                addTaskToCourse(req, resp);
                break;
            case "addTaskToStudent":
                addTaskToStudent(req, resp);
                break;
            case "cancelTaskToCourse":
                removeTaskFromCourse(req, resp);
                break;
            case "cancelTaskToStudent":
                removeTaskFromStudent(req, resp);
                break;
            case "null":
                resp.sendRedirect("task");
                break;
        }
    }



    private void assignTaskToStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        Task task = taskService.findTaskById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("tasksToStudent.jsp");
        req.setAttribute("taskToAssign", task);
        dispatcher.forward(req, resp);
    }

    private void addTaskToStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        Task task = taskService.findTaskById(id);
        int idStudent = Integer.parseInt(req.getParameter(ID_STUDENT));
        Student student = studentService.findStudentById(idStudent);
        taskService.assignToStudent(task, student);
        resp.sendRedirect("teacher");

    }

    private void removeTaskFromStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        Task task = taskService.findTaskById(id);
        int idStudent = Integer.parseInt(req.getParameter(ID_STUDENT));
        Student  student = studentService.findStudentById(idStudent);
        taskService.cancelAssignmentToStudent(task, student);
        resp.sendRedirect("teacher");
    }

    private void assignTaskToCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        Task task = taskService.findTaskById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("tasksToCourse.jsp");
        req.setAttribute("taskToAssign", task);
        dispatcher.forward(req, resp);
    }

    private void addTaskToCourse(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        Task task = taskService.findTaskById(id);
        int idCourse = Integer.parseInt(req.getParameter(ID_COURSE));
        Course course = adminService.findCourse(idCourse);
        taskService.assignToCourse(task, course);
        resp.sendRedirect("task");
    }

    private void removeTaskFromCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        Task task = taskService.findTaskById(id);
        int idCourse = Integer.parseInt(req.getParameter(ID_COURSE));
        Course course = adminService.findCourse(idCourse);
        taskService.cancelAssignmentToCourse(task, course);
        resp.sendRedirect("task");
    }

    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        String description = req.getParameter(DESCRIPTION);
        String review = req.getParameter(REVIEW);
        taskService.update(id, description, review);
        resp.sendRedirect("task");
    }



    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_TASK));
        taskService.deleteById(id);
        resp.sendRedirect("task");
    }

    private void saveTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter(DESCRIPTION);
        taskService.add(description);
        resp.sendRedirect("task");
    }
}
