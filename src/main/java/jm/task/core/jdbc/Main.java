package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();
//        userService.dropUsersTable(); //Использую для удаление таблицы
        userService.createUsersTable(); //1

        userService.saveUser("Name1", "LastName1", (byte) 20);//4
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1); //5
        userService.getAllUsers(); //6
        userService.cleanUsersTable(); //3
        userService.dropUsersTable(); //2

    }
}
