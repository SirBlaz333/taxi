package com.Serebriakov.database;

public abstract class SQLQuery {

    public static class UserQuery{
        public static final String ADD_USER = "INSERT INTO users VALUES(DEFAULT, ?, 1);";
        public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
        public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
        public static final String GET_ALL_USERS = "SELECT * FROM users";
        public static final String GET_USER_ROLE = "SELECT * FROM roles WHERE id = ?";
    }

    public static class ReceiptQuery{
        public static final String ADD_RECEIPT = "INSERT INTO receipts VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        public static final String GET_RECEIPT_BY_ID = "SELECT * FROM receipts WHERE id = ?";
        public static final String GET_RECEIPTS_BY_USER_ID = "SELECT * FROM receipts WHERE user_id = ?";
    }
}
