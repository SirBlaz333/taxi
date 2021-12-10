package com.Serebriakov.model.listener;

import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.CheckUnconfirmedReceipts;
import com.Serebriakov.model.util.ReceiptContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final long DIF = 5*100;
    private static final long PERIOD = 2*60*1000;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        //logging
        String path = ctx.getRealPath("WEB-INF/log4j2.log");
        System.setProperty("logFile", path);
        final Logger log = LogManager.getLogger("Listener");
        log.info("Log file path = " + path);

        //i18n;
        String localesFileName = ctx.getInitParameter("locales");
        String localesFileRealPath = ctx.getRealPath(localesFileName);
        Properties locales = new Properties();
        try{
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e){
            log.error("Cannot obtain locales");
        }
        ctx.setAttribute("locales", locales);
        log.info("Locales: " + locales);
        //New thread for checking unconfirmed receipts;
        try {
            ReceiptContainer.updateList();
        } catch (DBException e) {
            log.error("Cannot update receipt list");
        }
        new Thread(() -> {
            try {
                while(true){
                    CheckUnconfirmedReceipts.execute();
                    Thread.sleep(PERIOD);
                }
            } catch (InterruptedException e) {
                log.error("Thread has been interrupted: " + e.getMessage());
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }
}
