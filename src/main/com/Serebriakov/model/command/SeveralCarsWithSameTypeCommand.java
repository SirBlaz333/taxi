package com.Serebriakov.model.command;

import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.entity.type.Car_type;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeveralCarsWithSameTypeCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        CarDAO carDAO = CarDAOImpl.getInstance();
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();

        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        int passengers = receipt.getPassengers();
        String carType = receipt.getCarType();

        try{
            List<Car> cars = carDAO.findCarList(passengers, Car_type.getType(carType));
            if(cars == null){
                request.getSession().setAttribute("NoSuchCarErrorID", 2);
                return "choose_options.jsp";
            }
            request.getSession().setAttribute("amountOfCars", cars.size());

            Car[] carsArray = new Car[cars.size()];

            receipt.setPrice(receipt.getPrice()*cars.size());
            int id = receiptDAO.addReceipt(receipt, cars.toArray(carsArray));
            receipt.setId(id);
        } catch (DBException e){
            logger.error("Error: " + e.getMessage());
        }

        return "confirm_receipt.jsp";
    }
}
