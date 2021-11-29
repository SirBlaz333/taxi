package com.Serebriakov.database;

public abstract class SQLQuery {

    public static class UserQuery{
        public static final String ADD_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, 1, 0, ?);";
        public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
        public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
        public static final String GET_ALL_USERS = "SELECT * FROM users";
        public static final String GET_USER_ROLE = "SELECT * FROM roles WHERE id = ?";
        public static final String GET_DISCOUNT = "SELECT discount FROM discounts WHERE money <= ?";
        public static final String UPDATE_USER = "UPDATE users SET email = ?, name= ?, spent_money = ?, password = ? WHERE id = ?;";
    }

    public static class ReceiptQuery{
        public static final String GET_ALL_RECEIPTS = "SELECT * FROM receipts;";
        public static final String GET_RECEIPTS_WITH_STATE = "SELECT * FROM receipts WHERE receipt_state_id = ?;";
        public static final String ADD_RECEIPT = "INSERT INTO receipts VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String GET_RECEIPT_BY_ID = "SELECT * FROM receipts WHERE id = ?";
        public static final String GET_RECEIPTS_BY_USER_ID = "SELECT * FROM receipts WHERE user_id = ?";
        public static final String GET_STATE_ID = "SELECT * FROM receipt_states WHERE state = ?";
        public static final String DELETE_RECEIPT = "DELETE FROM receipts WHERE id = ?";
        public static final String CONFIRM_RECEIPT = "UPDATE receipts SET receipt_state_id = 2 WHERE id = ?;";
        public static final String FIND_LAST_RECEIPT_ID = "SELECT LAST_INSERT_ID();";
        public static final String ADD_CARS_TO_RECEIPT = "INSERT receipts_has_cars VALUES (?, ?);";
        public static final String FIND_CARS_BY_RECEIPT_ID = "SELECT cars_id FROM receipts_has_cars WHERE receipts_id = ?;";
        public static final String DELETE_CARS_FROM_RECEIPT = "DELETE FROM receipts_has_cars WHERE receipts_id = ?;";
        public static final String GET_STATE= "SELECT state FROM receipt_states WHERE id = ?";
    }

    public static class CarQuery{
        public static final String FIND_AVAILABLE_CAR = "SELECT * FROM cars WHERE max_passengers >= ? AND type_id = ? AND state_id = 1;";
        public static final String FIND_CAR_TYPE_ID = "SELECT * FROM types WHERE type = ?;";
        public static final String FIND_TARIFF = "SELECT * FROM tariffs WHERE types_id = ?";
        public static final String UPDATE_CAR_STATE = "UPDATE cars SET state_id = ? WHERE id = ?;";
        public static final String FIND_AVAILABLE_CAR_LIST = "select * from cars where type_id = ? AND state_id = 1 order by max_passengers DESC;";
        public static final String GET_CAR_BY_ID = "select * from cars where id = ?";
        public static final String GET_CAR_TYPE = "select * from types where id = ?";
        public static final String GET_CAR_STATE = "select * from car_states where id = ?";
    }
}
