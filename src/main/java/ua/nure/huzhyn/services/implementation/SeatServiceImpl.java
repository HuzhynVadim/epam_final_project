package ua.nure.huzhyn.services.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.SeatRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.SeatService;

import java.util.List;

public class SeatServiceImpl implements SeatService {
    private static final Logger LOGGER = Logger.getLogger(SeatServiceImpl.class);
    private SeatRepository seatRepository;
    private TransactionManager transactionManager;

    public SeatServiceImpl(SeatRepository seatRepository, TransactionManager transactionManager) {
        this.seatRepository = seatRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public int getCountSeat(String carId) {
        return transactionManager.execute(() -> seatRepository.getCountSeat(carId));
    }

    @Override
    public int getCountSeatByCarType(String trainId, CarType carType) {
        return transactionManager.execute(() -> seatRepository.getCountSeatByCarType(trainId, carType));
    }

    @Override
    public List<Seat> getSeatByCarId(String carId) {
        return transactionManager.execute(() -> seatRepository.getSeatByCarId(carId));
    }
}
