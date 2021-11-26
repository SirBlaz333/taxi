package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.state.Car_state;
import com.Serebriakov.database.entity.type.Car_type;
import com.Serebriakov.exception.DBException;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class CarDAOImplTest extends DBTest {
    private static CarDAO carDAO;
    private Car car;

    @BeforeClass
    public static void init() {
        String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&user=root&password=root";
        DatabaseManager.setURL(URL);
        carDAO = CarDAOImpl.getInstance();
    }

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
        car = new Car();
        car.setId(1);
        car.setType(Car_type.PUBLIC_HIRE_TAXI);
        car.setState(Car_state.AVAILABLE);
        car.setMaxPassengers(6);
    }

    @Test
    void updateCarState() throws DBException {
        System.out.println(DatabaseManager.getInstance().getConnection());
    }

    @Test
    void confirmCarForTrip() throws DBException {
        carDAO.confirmCarForTrip(1);
    }

    @Test
    void findCar() throws DBException {
        car = carDAO.findCar(6, Car_type.PUBLIC_HIRE_TAXI);
        Assert.assertEquals(1, car.getId());
    }

    @Test
    void findCar2() throws DBException {
        car = carDAO.findCar(6, Car_type.PUBLIC_HIRE_TAXI);
        Assert.assertNull(car);
    }

    @Test
    void findCarTypeId1() throws DBException {
        int id = carDAO.findCarTypeId(Car_type.PUBLIC_HIRE_TAXI);
        Assert.assertEquals(1, id);
    }

    @Test
    void findCarTypeId2() throws DBException {
        int id = carDAO.findCarTypeId(Car_type.MINICAB);
        Assert.assertEquals(2, id);
    }

    @Test
    void findCarTypeI3() throws DBException {
        int id = carDAO.findCarTypeId(Car_type.MINIBUS);
        Assert.assertEquals(3, id);
    }

    @Test
    void findPrice() throws DBException {
    }

    @Test
    void findCarList() throws DBException {
    }
}