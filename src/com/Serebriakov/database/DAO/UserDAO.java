package com.Serebriakov.database.DAO;

import com.Serebriakov.entity.type.Role;
import com.Serebriakov.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User getUserByLogin(String login) throws SQLException;

    User getUserById(int id) throws SQLException;

    Role getUserRole(int roleId) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    void deleteUser(User user);

    void updateUser(User user);

}
