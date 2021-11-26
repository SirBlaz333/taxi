package com.Serebriakov.model.command;

import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.model.util.ReceiptContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PageCommand implements Command {

    private static final int maxReceipts = 15;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        int page = (Integer) session.getAttribute("page");
        String direction = request.getParameter("direction");
        if(direction == null){
            direction = (String) session.getAttribute("direction");
        }
        if(direction.equals("next")){
            page++;
        }
        if(direction.equals("previous")){
            page--;
        }
        List<Receipt> receipts = ReceiptContainer.getRequiredReceipts(session.getId());
        int last = maxReceipts*page;
        if(last >= receipts.size()){
            last = receipts.size();
        }
        List<Receipt> requiredReceipts = receipts.subList((page-1)*maxReceipts, last);
        int maxPossiblePage = receipts.size()/maxReceipts + 1;
        session.setAttribute("page", page);
        session.setAttribute("maxPossiblePage", maxPossiblePage);
        session.setAttribute("requiredReceipts", requiredReceipts);
        return "admin.jsp";
    }
}
