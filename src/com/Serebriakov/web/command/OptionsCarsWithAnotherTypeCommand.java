package com.Serebriakov.web.command;

import com.Serebriakov.entity.type.Car_type;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class OptionsCarsWithAnotherTypeCommand implements Command {
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
