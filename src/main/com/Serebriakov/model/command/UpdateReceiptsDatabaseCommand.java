package com.Serebriakov.model.command;

import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.ReceiptContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateReceiptsDatabaseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        HttpSession session = request.getSession();
        ReceiptContainer.updateList();
        ReceiptContainer.createRequiredReceipts(session.getId());
        List<Receipt> receipts = ReceiptContainer.getRequiredReceipts(session.getId());
        session.setAttribute("requiredReceipts", receipts);
        session.setAttribute("page", 0);
        session.setAttribute("direction", "next");
        return new PageCommand().execute(request, response);
    }
}
