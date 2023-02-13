package DAO;

import Service.ClassService;
import model.Classes;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    ClassService classService;
    private final Connection connection;

    private final static String SELECT_ALL_STUDENT = "select * from student join class on class.id = student.student_id";
    private final static String SELECT_STUDENT_BY_ID = "select * from student where id=?;";
    private final static String INSERT_STUDENT = "insert into student(name , point , aclass) values(?,?,?);";
    private final static String UPDATE_STUDENT = "update student set name=?,point=? where id=?;";
    private final static String DELETE_STUDENT = "delete student where id=?";

    public StudentDAO() {
        connection = MyConnection.connection();
    }

    public List<Student> fillAll() {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_STUDENT)
        ) {
            while (resultSet.next()) {
                Classes aClass = classService.findClass(resultSet.getInt(4));
                students.add(new Student(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        aClass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student findById(int id) {
        Student student = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Classes classes = classService.findClass(resultSet.getInt(4));
                student = new Student(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        classes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


    public void addStudent(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setString(1, student.names());
            preparedStatement.setInt(2, student.points());
            preparedStatement.setInt(3, student.aClass().id());
            int row1 = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void edit(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setInt(1, student.id());
            preparedStatement.setString(2, student.names());
            preparedStatement.setInt(3, student.points());
            int row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
