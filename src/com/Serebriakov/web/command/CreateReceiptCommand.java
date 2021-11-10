package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.type.Car_types;
import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.User;
import com.Serebriakov.web.Command;

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
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user == null){
            request.getSession().setAttribute("errorMessage", "You are not logged in");
            return "error_page.jsp";
        }

        int length = new SecureRandom().nextInt(850)+150;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        int passengers = Integer.parseInt(request.getParameter("passengers"));
        String carType = request.getParameter("car_type");
        Car car = carDAO.findCar(passengers, Car_types.getType(carType));

        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        Receipt receipt = Receipt.createReceipt(user, null, carType, length, time, passengers, departure, destination);

        if(car == null){
            request.getSession().setAttribute("NoSuchCarError", "There are no available cars with this type");
            request.getSession().setAttribute("currentReceipt", receipt);
            return "choose_page.jsp";
        }

        receipt.setCarId(car.getId());

        int id = receiptDAO.addReceipt(receipt);
        receipt.setId(id);
        request.getSession().setAttribute("currentReceipt", receipt);
        return "confirm_receipt_page.jsp";
    }
}
