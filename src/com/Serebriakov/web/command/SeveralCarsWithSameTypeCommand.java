package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.type.Car_type;
import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SeveralCarsWithSameTypeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        CarDAO carDAO = CarDAOImpl.getInstance();
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();

        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        int passengers = receipt.getPassengers();
        String carType = receipt.getCarType();

        List<Car> cars = carDAO.findCarList(passengers, Car_type.getType(carType));
        if(cars == null){
            request.getSession().setAttribute("NoSuchCarError", "There are no available cars with such type.<br> Please choose another option");
            return "choose_page.jsp";
        }
        request.getSession().setAttribute("amountOfCars", cars.size());

        Car[] carsArray = new Car[cars.size()];

        receipt.setPrice(receipt.getPrice()*cars.size());
        int id = receiptDAO.addReceipt(receipt, cars.toArray(carsArray));
        receipt.setId(id);

        return "confirm_receipt_page.jsp";
    }
}
