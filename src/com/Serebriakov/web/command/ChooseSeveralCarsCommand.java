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
import java.sql.SQLException;
import java.util.List;

public class ChooseSeveralCarsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        CarDAO carDAO = new CarDAOImpl();
        ReceiptDAO receiptDAO = new ReceiptDAOImpl();

        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        int passengers = receipt.getPassengers();
        String carType = receipt.getCarType();

        List<Car> cars = carDAO.findCarList(passengers, Car_types.getType(carType));
        if(cars.size() == 0){
            request.getSession().setAttribute("NoSuchCarsError", "There are no available cars with such type");
            return "choose_page.jsp";
        }
        request.getSession().setAttribute("amountOfCars", cars.size());

        receipt.setPrice(receipt.getPrice()*cars.size());
        int id = receiptDAO.addReceipt(receipt);
        receipt.setId(id);
        request.getSession().setAttribute("currentReceipt", receipt);

        return "confirm_receipt_page.jsp";
    }
}
