package com.Serebriakov.model.util;

import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.entity.state.Receipt_state;
import com.Serebriakov.database.entity.type.Car_type;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptCreator {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    public static Receipt getReceipt(HttpServletRequest request, User user) throws DBException {
        CarDAO carDAO = CarDAOImpl.getInstance();
        String carType = request.getParameter("car_type");

        Receipt receipt = new Receipt();

        int length = new SecureRandom().nextInt(850)+150;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        int passengers = Integer.parseInt(request.getParameter("passengers"));

        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");

        receipt.setCarType(carType);
        receipt.setPassengers(passengers);
        receipt.setUserId(user.getId());
        receipt.setDeparture(departure);
        receipt.setDestination(destination);
        receipt.setTime(time);
        receipt.setLength(length);
        receipt.setPricePerKm(carDAO.findPrice(Car_type.getType(carType), length));
        receipt.setPrice(receipt.getPricePerKm()*length);
        receipt.setState(Receipt_state.CREATED);
        return receipt;
    }
}
