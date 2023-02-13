package Service;

import DAO.ClassDAO;
import model.Classes;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassService {
    ClassDAO classDAO = new ClassDAO();

    public ClassService() {
    }

    List<Classes> classes = new ArrayList<>();

    public List<Classes> findAll() {
        return classDAO.findAll();
    }

    public void addClass(Classes aClass) {
        classDAO.addClass(aClass);
    }


    public Classes findClass(int id) {
        return classDAO.findById(id);
    }

    public void delete(int id) {
        classDAO.delete(id);
    }

    public void update(Classes clas) {
        classDAO.update(clas);
    }
}
