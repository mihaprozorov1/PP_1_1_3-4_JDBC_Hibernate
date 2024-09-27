package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDaoHibernate = new UserDaoHibernateImpl();

    // 1) Создание таблицы для User(ов) — не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    // 2) Удаление таблицы User(ов) — не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();
    }

    // 4) Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем — name добавлен в базу данных");
    }

    // 5) Удаление User из таблицы (по id)
    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);
    }

    // 6) Получение всех User(ов) из таблицы
    public List<User> getAllUsers() throws SQLException {
        return userDaoHibernate.getAllUsers();
    }

    // 3) Очистка содержания таблицы
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
