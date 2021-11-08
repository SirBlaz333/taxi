package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.state.Receipt_states;
import com.Serebriakov.database.type.Car_types;
import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateReceiptCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        CarDAO carDAO = new CarDAOImpl();
        ReceiptDAO receiptDAO = new ReceiptDAOImpl();

        String carType = request.getParameter("car_type");
        int passengers = Integer.parseInt(request.getParameter("passengers"));
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");

        Receipt receipt = new Receipt();

        User user = (User) request.getSession().getAttribute("currentUser");
        receipt.setUserId(user.getId());

        Car car = carDAO.findCar(passengers, Car_types.getType(carType));
        receipt.setCarId(car.getId());

        receipt.setDeparture(departure);
        receipt.setDestination(destination);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        receipt.setDate(dtf.format(now));

        double length = new SecureRandom().nextDouble()*8.5+1.5;
        receipt.setLength(length);

        receipt.setPrice(carDAO.findPrice(car.getType(), length));

        receipt.setState(Receipt_states.CREATED);
        request.getSession().setAttribute("currentReceipt", receipt);

        System.out.println(1);
        receiptDAO.addReceipt(receipt);
        System.out.println(1);
        return "confirmation_page.jsp";
    }
}
