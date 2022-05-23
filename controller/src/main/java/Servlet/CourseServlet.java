package Servlet;

import courses.dao.EntityDaoImplCourse;
import courses.entity.Course;
import managment.implementation.CourseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseServlet", value = "/course")
public class CourseServlet extends HttpServlet {

    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String HOURS = "hours";

    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    private CourseServiceImpl courseService = new CourseServiceImpl(new EntityDaoImplCourse());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<Course> courseList = courseService.findAll();
        req.setAttribute("courses", courseList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("course.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        String action = req.getParameter(ACTION);
        switch (action) {
            case "delete":
                deleteCourse(req, resp);
                break;
            case "add":
                saveCourse(req, resp);
                break;
            case "update":
                updateCourse(req, resp);
                break;
        }
    }

    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ID));
        courseService.deleteById(id);
        resp.sendRedirect("course");
    }

    private void saveCourse(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String description = req.getParameter(DESCRIPTION);
        String hours = req.getParameter(HOURS);
        courseService.register(description, hours);
        resp.sendRedirect("course");

    }

    private void updateCourse(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ID));
        String description = req.getParameter(DESCRIPTION);
        String hours = req.getParameter(HOURS);
        courseService.update(id, description, hours);
        resp.sendRedirect("course");
    }


}
