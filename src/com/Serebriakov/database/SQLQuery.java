package com.Serebriakov.database;

public abstract class SQLQuery {

    public static class UserQuery{
        public static final String ADD_USER = "INSERT INTO users VALUES(DEFAULT, ?, 1);";
        public static final String GET_USER_BY_LOGIN = "SELECT id, login, roles_id FROM users WHERE login = ?";
        public static final String GET_USER_BY_ID = "SELECT id, login, roles_id FROM users WHERE id = ?";
        public static final String GET_ALL_USERS = "SELECT * FROM users";
    }
}
