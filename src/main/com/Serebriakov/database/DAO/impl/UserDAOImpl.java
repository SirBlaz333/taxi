package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.entity.type.Role;
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

    private static Logger logger = LogManager.getLogger("DB");
    private static DatabaseManager dbManager;
    private static UserDAOImpl userDAO;

    static {
        logger.info("Class " + UserDAOImpl.class.getName() + " has been uploaded");
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
        User user;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            user = getUser(preparedStatement);
            String message;
            if(user != null){
                message = String.format("User(%s) received from database", login);
            } else {
                message = String.format("Cannot receive user(%s) from database ", login);
            }
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get user by login (" + login + ")");
        }
        return user;
    }

    private User getUser(PreparedStatement preparedStatement) throws SQLException, DBException {
        User user = null;
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            user = createUser(rs);
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            User user = getUser(preparedStatement);
            String message;
            if(user != null){
                message = String.format("User(%s) received from database", id);
            } else {
                message = String.format("Cannot receive user(%s) from database ", id);
            }
            logger.info(message);
            return user;
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get user by id (" + id + ")");
        }
    }

    private User createUser(ResultSet rs) throws SQLException, DBException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        int roleId = rs.getInt("role_id");
        user.setRole(getUserRole(roleId));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        int spentMoney = rs.getInt("spent_money");
        user.setSpentMoney(spentMoney);
        user.setDiscount(getDiscount(spentMoney));
        return user;
    }

    public int getDiscount(int spentMoney) throws DBException{
        int discount = 0;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_DISCOUNT)){
            ps.setInt(1, spentMoney);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                discount = rs.getInt("discount");
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get discount");
        }
        return discount;
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
            throw new DBException("Cannot get role for user");
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
                User user = createUser(rs);
                users.add(user);
            }
            logger.info("All users have been received");
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get all users");
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
            preparedStatement.setString(4, user.getName());
            preparedStatement.execute();
            String message = String.format("User(%s) has been added to database", user.getLogin());
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot add user");
        }
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)){
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getSpentMoney());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.execute();
            String message = String.format("User(%s) has been updated in database", user.getLogin());
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot update user");
        }
    }
}
