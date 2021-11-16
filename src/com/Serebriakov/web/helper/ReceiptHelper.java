package com.Serebriakov.web.helper;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.state.Receipt_state;
import com.Serebriakov.database.type.Car_type;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptHelper {
    public static Receipt getReceipt(HttpServletRequest request, User user) throws IOException, SQLException {
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
