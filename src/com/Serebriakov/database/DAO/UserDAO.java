package com.Serebriakov.database.DAO;

import com.Serebriakov.database.entity.User;
import com.Serebriakov.database.entity.type.Role;
import com.Serebriakov.exception.DBException;

import java.util.List;

public interface UserDAO {

    User getUserByLogin(String login) throws DBException;

    User getUserById(int id) throws DBException;

    Role getUserRole(int roleId) throws DBException;

    List<User> getAllUsers() throws DBException;

    void addUser(User user) throws DBException;

    void deleteUser(User user);

    void updateUser(User user);

}
