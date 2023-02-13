package Service;

import DAO.StudentDAO;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = null;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    List<Student> students = new ArrayList<>();

    public List<Student> fillAll() {
        return studentDAO.fillAll();
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public Student findStudentById(int id) {
        return studentDAO.findById(id);
    }

    //    public int findIndexById(int id) {
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).id() == id) {
//                return i;
//            }
//        }
//        return 1;
//    }
    public void delete(int id) {
        studentDAO.delete(id);
    }

    public void edit(Student student) {
        studentDAO.edit(student);
    }


}
