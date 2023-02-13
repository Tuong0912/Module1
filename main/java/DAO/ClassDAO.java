package DAO;

import model.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private Connection connection = null;

    private final static String SELECT_ALL_CLASS = "select * from class ;";
    private final static String SELECT_CLASS_BY_ID = "select * from class where id=?;";
    private final static String INSERT_CLASS = "insert into class(className , schoolName) values(? , ?);";
    private final static String UPDATE_CLASS = "update class set className=?,schoolName=? where id=?;";
    private final static String DELETE_CLASS = "delete class where id=?;";


    public ClassDAO() {
        connection = MyConnection.connection();
    }

    public List<Classes> findAll() {
        List<Classes> classes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classes.add(new Classes(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

    public Classes findById(int id) {
        Classes classes = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classes = new Classes(resultSet.getInt(1)
                        , resultSet.getString(2),
                        resultSet.getString(3));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

    public void addClass(Classes classes) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASS)) {
            preparedStatement.setString(1, classes.className());
            preparedStatement.setString(2, classes.schoolName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Classes classes) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLASS)) {
            preparedStatement.setString(1, classes.className());
            preparedStatement.setString(2, classes.schoolName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLASS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
