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

@WebListener
public class ContextListener implements ServletContextListener {
    private static final long DIF = 5*100;
    private static final long PERIOD = 2*60*1000;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        String path = ctx.getRealPath("WEB-INF/log4j2.log");
        System.setProperty("logFile", path);

        final Logger log = LogManager.getLogger(ContextListener.class);
        log.debug("Log file path = " + path);
        try {
            ReceiptContainer.updateList();
        } catch (DBException e) {
            e.printStackTrace();
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
