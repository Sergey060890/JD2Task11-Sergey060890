package Servlet;

import DTO.CourseDTO;
import courses.dao.EntityDaoImplAdmin;
import courses.dao.EntityDaoImplTeacher;
import managment.implementation.AdminServiceImpl;
import managment.implementation.TeacherServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeacherServiceServlet", value = "/teacherService")
public class TeacherServiceServlet extends HttpServlet {

    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    public static final String ACTION = "action";
    public String teacherSurname = "";
    TeacherServiceImpl teacherService = new TeacherServiceImpl(new EntityDaoImplTeacher());
    AdminServiceImpl adminService = new AdminServiceImpl(new EntityDaoImplAdmin());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<CourseDTO> listOfCourseAndTeacher = teacherService.findCoursesForTeacher("Кириллов");
        req.setAttribute("listOfAllCourses", listOfCourseAndTeacher);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacherService.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        String action = req.getParameter(ACTION);
        switch (action) {
            case "search":
                search(req, resp);
                break;
            default:
                resp.sendRedirect("teacherService");
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String teacherSurnames = req.getParameter("teacherSurname");
        List<CourseDTO> courseDTO = teacherService.findCoursesForTeacher(teacherSurnames);
        req.setAttribute("listOfAllCourses", courseDTO);
        req.getRequestDispatcher("/teacherService.jsp").forward(req, resp);
    }
}
