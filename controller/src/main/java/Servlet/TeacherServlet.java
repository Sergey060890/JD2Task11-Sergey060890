package Servlet;

import courses.dao.EntityDao;
import courses.dao.EntityDaoImplTeacher;
import courses.entity.Teacher;
import managment.implementation.TeacherServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeacherServlet", value = "/teacher")
public class TeacherServlet extends HttpServlet {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String ACTION = "action";

    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    private EntityDao daoTeacher = new EntityDaoImplTeacher();

    private TeacherServiceImpl teacherService = new TeacherServiceImpl(new EntityDaoImplTeacher());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        List<Teacher> teacherList = teacherService.findAll();
        req.setAttribute("teacher", teacherList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("teacher.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        resp.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        String action = req.getParameter(ACTION);
        switch (action) {
            case "add":
                saveTeacher(req, resp);
                break;
            case "delete":
                deleteTeacher(req, resp);
                break;
            case "update":
                updateTeacher(req, resp);
                break;
        }
    }

    private void deleteTeacher(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        teacherService.deleteById(id);
        resp.sendRedirect("teacher");
    }

    private void saveTeacher(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        teacherService.register(name, surname);
        resp.sendRedirect("teacher");
    }


    private void updateTeacher(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        teacherService.update(id, name, surname);
        resp.sendRedirect("teacher");
    }
}
