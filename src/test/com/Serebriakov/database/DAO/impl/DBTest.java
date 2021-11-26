package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DatabaseManager;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

    @Before
    public void setUp() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.addBatch("CREATE SCHEMA IF NOT EXISTS `taxi` DEFAULT CHARACTER SET utf8 ;");
        statement.addBatch("USE `taxi` ;");
        statement.addBatch("DROP TABLE IF EXISTS `taxi`.`car_states` ;\n" +
                "CREATE TABLE IF NOT EXISTS `taxi`.`car_states` (\n" +
                "  `id` INT NOT NULL,\n" +
                "  `state` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;");
        statement.addBatch("DROP TABLE IF EXISTS `taxi`.`types` ;\n" +
                "CREATE TABLE IF NOT EXISTS `taxi`.`types` (\n" +
                "  `id` INT NOT NULL,\n" +
                "  `type` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;\n");
        statement.addBatch("DROP TABLE IF EXISTS `taxi`.`cars` ;\n" +
                "CREATE TABLE IF NOT EXISTS `taxi`.`cars` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `max_passengers` INT NOT NULL,\n" +
                "  `type_id` INT NOT NULL,\n" +
                "  `state_id` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;");
        statement.addBatch("INSERT INTO car_states VALUES(1, 'available to order');\n" +
                "INSERT INTO car_states VALUES(2, 'is on a trip');\n" +
                "INSERT INTO car_states VALUES(3, 'not active');");
        statement.addBatch("INSERT INTO types VALUES (1, 'Public hire taxi');\n" +
                "INSERT INTO types VALUES (2, 'Minicab');\n" +
                "INSERT INTO types VALUES (3, 'Minibus');");
        statement.addBatch("INSERT INTO cars VALUES (DEFAULT, 6, 1, 1);\n" +
                "INSERT INTO cars VALUES (DEFAULT, 30, 3, 1);");
        statement.executeBatch();
    }
}
