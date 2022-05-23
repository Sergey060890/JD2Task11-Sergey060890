package Servlet;

import courses.dao.EntityDaoImplAdmin;
import courses.entity.Course;
import courses.entity.Student;
import courses.entity.Task;
import managment.implementation.AdminServiceImpl;
import managment.implementation.StudentServiceImpl;
import managment.interfaces.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    public static final String ID_STUDENT = "id";
    public static final String ACTION = "action";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    public static final String ID_COURSE = "idCourse";
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private AdminService adminService = new AdminServiceImpl(new EntityDaoImplAdmin());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<Student> studentList = studentService.findAll();
        req.setAttribute("students", studentList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("student.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<Course> courseList = adminService.listAllCourses();
        req.setAttribute("courses", courseList);
        String action = req.getParameter(ACTION);

        switch (action) {
            case "add":
                saveStudent(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "update":
                updateStudent(req, resp);
                break;
            case "enroll":
                enrollStudent(req, resp);
                break;
            case "coursesToEnroll":
                chooseCoursesToEnroll(req, resp);
                break;
            case "coursesToCancelEnroll":
                chooseCoursesToCancelEnrollment(req, resp);
                break;
        }

    }

    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT));
        Task task = studentService.searchInTasks(id);
        req.setAttribute("task", task);
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT));
        studentService.deleteById(id);
        resp.sendRedirect("student");
    }

    private void saveStudent(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        studentService.register(name, surname);
        resp.sendRedirect("student");
    }


    private void updateStudent(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT));
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        studentService.update(id, name, surname);
        resp.sendRedirect("student");    }

    private void enrollStudent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT));
        Student student = studentService.findStudentById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("coursesToEnroll.jsp");
        req.setAttribute("studentToEnroll", student);
        dispatcher.forward(req, resp);
    }

    private void chooseCoursesToEnroll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT));
        Student student = studentService.findStudentById(id);
        int idCourse = Integer.parseInt(req.getParameter(ID_COURSE));
        Course course = adminService.findCourse(idCourse);
        studentService.enrollInCourse(student, course);
        resp.sendRedirect("student");
    }

    private void chooseCoursesToCancelEnrollment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT));
        Student student = studentService.findStudentById(id);
        int idCourse = Integer.parseInt(req.getParameter(ID_COURSE));
        Course course = adminService.findCourse(idCourse);
        studentService.cancelEnrollment(student, course);
        resp.sendRedirect("student");
    }

}


