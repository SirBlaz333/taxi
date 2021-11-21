package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.entity.User;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.entity.type.Role;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Serebriakov.database.SQLQuery.UserQuery.*;

public class UserDAOImpl implements UserDAO {

    private static Logger logger = LogManager.getLogger("DBError");
    private static DatabaseManager dbManager;
    private static UserDAOImpl userDAO;

    static {
        userDAO = null;
    }

    private UserDAOImpl(){
        dbManager = DatabaseManager.getInstance();
    }

    public static synchronized UserDAOImpl getInstance(){
        if(userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        User user = null;
        try(Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Role role = getUserRole(roleId);
                user = new User(id, login, password, email, role);
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return user;
    }



    @Override
    public User getUserById(int id) throws DBException {
        User user = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String login = rs.getString("login");
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Role role = getUserRole(roleId);
                user = new User(id, login, password, email, role);
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return user;
    }

    @Override
    public Role getUserRole(int roleId) throws DBException {
        Role role = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ROLE)){
            preparedStatement.setInt(1, roleId);
            ResultSet rs = preparedStatement.executeQuery();
            String result = null;
            while(rs.next()){
                result = rs.getString("role");
            }
            if(result!=null){
                role = Role.getRole(result);
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return role;
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String login = rs.getString("login");
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Role role = getUserRole(roleId);
                User user = new User(id, login, password, email, role);
                users.add(user);
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return users;
    }

    @Override
    public void addUser(User user) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.execute();
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
