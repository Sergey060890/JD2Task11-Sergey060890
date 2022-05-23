package Servlet;

import DTO.CourseDTO;
import courses.dao.EntityDaoImplAdmin;
import courses.entity.Course;
import courses.entity.Teacher;
import managment.implementation.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    public static final String ID = "id";
    public static final String ACTION = "action";
    public static final String ID_TEACHER = "idTeacher";


    private AdminServiceImpl adminService = new AdminServiceImpl(new EntityDaoImplAdmin());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<CourseDTO> listOfCourseAndTeacher = adminService.listOfAllCourses();
        req.setAttribute("listOfAllCourses", listOfCourseAndTeacher);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<Teacher> teacherList = adminService.listAllTeachers();
        req.setAttribute("listTeachers", teacherList);

        String action = req.getParameter(ACTION);

        switch (action) {
            case "enroll":
                enrollCourse(req, resp);
                break;
            case "teacherToEnroll":
                chooseTeacherToEnroll(req, resp);
                break;
            case "cancelToEnroll":
                cancelTeacherEnroll(req, resp);
                break;
        }
    }


    private void enrollCourse(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        Course course = adminService.findCourse(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("teacherToEnroll.jsp");
        req.setAttribute("courseEnroll", course);
        dispatcher.forward(req, resp);
    }

    private void chooseTeacherToEnroll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        Course course = adminService.findCourse(id);
        int idTeacher = Integer.parseInt(req.getParameter(ID_TEACHER));
        Teacher teacher = adminService.findTeacher(idTeacher);
        adminService.enrollTeacher(teacher, course);
        resp.sendRedirect("admin");
    }

    private void cancelTeacherEnroll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        Course course = adminService.findCourse(id);
        int idTeacher = Integer.parseInt(req.getParameter(ID_TEACHER));
        Teacher teacher = adminService.findTeacher(idTeacher);
        adminService.cancelEnrollTeacher(teacher, course);
        resp.sendRedirect("admin");
    }
}
