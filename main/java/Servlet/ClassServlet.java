package Servlet;

import Service.ClassService;
import model.Classes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ClassServlet", value = "/class")
public class ClassServlet extends HttpServlet {
    private final ClassService classService;

    public ClassServlet() {
        classService = new ClassService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                deleteClass(request, response);
                break;
            case "create":
                createForm(request, response);
                break;
            case "update":
                updateFormClass(request, response);
                break;
            default:
                disPlayClass(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createClass(request, response);
                break;
            case "update":
                updateClass(request, response);
                break;
            default:
                disPlayClass(request, response);
        }

    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String className = request.getParameter("className");
        String schoolName = request.getParameter("schoolName");
        classService.update(new Classes(id, className, schoolName));
        response.sendRedirect("/class");
    }

    private void createClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String className = request.getParameter("className");
        String schoolName = request.getParameter("schoolName");
        classService.addClass(new Classes(className, schoolName));
        response.sendRedirect("/class");
    }

    private void updateFormClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("class/update.jsp");
        request.setAttribute("clas", classService.findClass(id));
        dispatcher.forward(request, response);
    }

    private void disPlayClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("class/list.jsp");
        request.setAttribute("clas", classService.findAll());
        dispatcher.forward(request, response);
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("class/create.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        classService.delete(id);
        response.sendRedirect("/class");
    }

}
