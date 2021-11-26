package com.Serebriakov.model.command;

import com.Serebriakov.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws DBException;

}
