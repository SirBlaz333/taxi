package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.entity.type.Car_type;
import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.User;
import com.Serebriakov.web.Command;
import com.Serebriakov.web.helper.ReceiptHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateReceiptCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        CarDAO carDAO = CarDAOImpl.getInstance();
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user == null){
            request.getSession().setAttribute("errorMessage", "You are not logged in");
            return "error_page.jsp";
        }
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        String carType = request.getParameter("car_type");
        Car car;
        if(receipt != null){
            car = carDAO.findCar(receipt.getPassengers(), Car_type.getType(carType));
            receipt.setCarType(carType);
            receipt.setPricePerKm(carDAO.findPrice(Car_type.getType(carType), receipt.getLength()));
            receipt.setPrice(receipt.getPricePerKm()*receipt.getLength());
        } else {
            receipt = ReceiptHelper.getReceipt(request, user);
            request.getSession().setAttribute("currentReceipt", receipt);
            int passengers = Integer.parseInt(request.getParameter("passengers"));
            car = carDAO.findCar(passengers, Car_type.getType(carType));
        }

        if(car == null){
            request.getSession().setAttribute("NoSuchCarError", "There are no available taxi with this type");
            return "choose_page.jsp";
        }

        int id = receiptDAO.addReceipt(receipt, car);
        receipt.setId(id);

        return "confirm_receipt_page.jsp";
    }
}
