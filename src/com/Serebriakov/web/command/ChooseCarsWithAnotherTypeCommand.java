package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.type.Car_types;
import com.Serebriakov.entity.Car;
import com.Serebriakov.web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChooseCarsWithAnotherTypeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        CarDAO carDAO = new CarDAOImpl();

        int passengers = Integer.parseInt(request.getParameter("passengers"));
        String carType = request.getParameter("car_type");
        Car_types type = Car_types.getType(carType);

        List<Car> cars = new ArrayList<>();
        for(Car_types types : Car_types.values()){
            if(!types.equals(type)){
                cars.add(carDAO.findCar(passengers, types));
            }
        }

        return null;
    }
}
