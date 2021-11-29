package com.Serebriakov.model.util;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.state.Car_state;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckUnconfirmedReceipts{
    private static long DIF = 5*60;
    private static Logger logger = LogManager.getLogger("Listener");
    public static void execute() {
        try {
            ReceiptDAOImpl receiptDAO = ReceiptDAOImpl.getInstance();
            CarDAO carDAO = CarDAOImpl.getInstance();
            List<Receipt> receipts = receiptDAO.getAllReceiptsWithSuchState(1);
            logger.info("Checking for unconfirmed receipts");
            for(Receipt receipt : receipts){
                if(needToBeDeleted(receipt)){
                    String message = String.format("Trying to delete receipt(%d) from database", receipt.getId());
                    logger.info(message);
                    List<Integer> carsId = receiptDAO.getCarsIdByReceiptId(receipt.getId());
                    for(int carId : carsId){
                        Car car = new Car();
                        car.setId(carId);
                        car.setState(Car_state.AVAILABLE);
                        carDAO.updateCarState(car);
                    }
                    receiptDAO.deleteReceipt(receipt.getId());
                }
            }
        } catch (DBException e) {
            logger.error("Cannot delete created receipts");
        }
    }

    private static boolean needToBeDeleted(Receipt receipt){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(receipt.getTime(), formatter);
        long after = now.atZone(ZoneId.systemDefault()).toEpochSecond();
        long before = time.atZone(ZoneId.systemDefault()).toEpochSecond();
        return (after - before > DIF);
    }
}
