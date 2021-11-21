package com.Serebriakov.web.command;

import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.type.Car_type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class OptionsCarsWithAnotherTypeCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        Object options = request.getSession().getAttribute("carTypeOptions");
        if(options == null){
            String carType = receipt.getCarType();
            List<String> carTypeOptions = new ArrayList<>();
            for(Car_type type : Car_type.values()){
                if(type != Car_type.getType(carType)){
                    carTypeOptions.add(Car_type.getStringType(type));
                }
            }
            request.getSession().setAttribute("carTypeOptions", carTypeOptions);
        }
        return "cars_with_another_type.jsp";
    }
}
