package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    // 1) Создание таблицы для User(ов) — не должно приводить к исключению, если такая таблица уже существует(1)
    public void createUsersTable() {
        Statement statement = null;
        String sql = "CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Connection OK in - createUsersTable(1)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR in - createUsersTable(1)");
        }
    }

    // 2) Удаление таблицы User(ов) — не должно приводить к исключению, если таблицы не существует(2)
    public void dropUsersTable() {
        Statement statement = null;

        String sql = "DROP TABLE users";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Connection OK in - dropUsersTable(2)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR in - dropUsersTable(2)");
        }
    }

    // 3) Добавление User в таблицу(4)
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (Name, LastName, Age) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
            System.out.println("Connection OK in - saveUser(3)");

        } catch (SQLNonTransientConnectionException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR in - saveUser(3)");
        }
    }

    // 4) Удаление User из таблицы (по id)(5)
    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM users WHERE Id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Connection OK in - removeUserById(4)");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR in - removeUserById(4)");
        }
    }

    // 5) Получение всех User(ов) из таблицы(6)
    public List<User> getAllUsers() {
        List<User> addAllUsers = new ArrayList<>();
        String sql = "SELECT ID, Name, LastName, Age FROM Users";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));
                addAllUsers.add(user);
                System.out.println("Connection OK in - getAllUsers(5)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR in - getAllUsers(5)");
        }
        return addAllUsers;
    }

    // 3) Очистка содержания таблицы
    public void cleanUsersTable() {
        Statement statement = null;
        String sql = "DELETE FROM users";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Connection OK in - cleanUsersTable(6)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR in - cleanUsersTable(6)");
        }
    }
}
