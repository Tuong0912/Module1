package Servlet;

import Service.ClassService;
import Service.StudentService;
import model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private final StudentService studentService;
    private final ClassService classService;

    public StudentServlet(StudentService studentService, ClassService classService) {
        this.studentService = studentService;
        this.classService = classService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteStudent(request, response);
                break;
            case "detail":
                break;
            case "create":
                createFormStudent(request, response);
                break;
            case "update":
                updateForm(request, response);
                break;
            default:
                disPlayStudentList(request, response);

        }
    }

    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/update.jsp");
        request.setAttribute("students", studentService.findStudentById(id));
        request.setAttribute("classes", classService.findClass(id));
        dispatcher.forward(request, response);
    }

    private void createFormStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/create.jsp");
        request.setAttribute("students", studentService.fillAll());
        requestDispatcher.forward(request, response);
    }

    private void disPlayStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        request.setAttribute("students", studentService.fillAll());
        dispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.delete(id);
        response.sendRedirect("/student");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createStudent(request, response);
                break;
            case "update":
                updateStudent(request, response);
                break;
            default:
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int point = Integer.parseInt(request.getParameter("point"));
        int student_id = Integer.parseInt(request.getParameter("classes"));
        studentService.edit(new Student(id, name, point, classService.findClass(student_id)));
        response.sendRedirect("/students");

    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int point = Integer.parseInt(request.getParameter("point"));
        int student_Id = Integer.parseInt(request.getParameter("classes"));
        studentService.addStudent(new Student(name, point, classService.findClass(student_Id)));
        response.sendRedirect("/student");
    }

}


