package com.Serebriakov.util.tag;

import com.Serebriakov.entity.Receipt;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginValidationTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException{
        try{
            JspWriter out = getJspContext().getOut();
            Receipt receipt = (Receipt) getJspContext().getAttribute("source");
            out.print("Hello");
        } catch (IOException e){
            throw new JspException("Error" + e.getMessage());
        }
    }
}
