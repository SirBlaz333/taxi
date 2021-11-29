package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.database.entity.type.Car_type;
import com.Serebriakov.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowReceiptCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String idParameter = request.getParameter("id");
        int id = Integer.parseInt(idParameter);
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();
        UserDAO userDAO = UserDAOImpl.getInstance();
        CarDAO carDAO = CarDAOImpl.getInstance();
        Receipt receipt = receiptDAO.getReceiptById(id);
        User user = userDAO.getUserById(receipt.getUserId());
        receipt.setUser(user);
        List<Integer> cars_id = receiptDAO.getCarsIdByReceiptId(receipt.getId());
        Car car = carDAO.getCar(cars_id.get(0));
        String type = Car_type.getStringType(car.getType());
        request.getSession().setAttribute("receiptInfo", receipt);
        request.getSession().setAttribute("carTypeInfo", type);
        return "receipt_info.jsp";
    }
}
