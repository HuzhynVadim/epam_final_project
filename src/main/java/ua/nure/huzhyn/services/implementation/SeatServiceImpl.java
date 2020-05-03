package ua.nure.huzhyn.services.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.SeatRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.SeatService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeatServiceImpl implements SeatService {
    private static final Logger LOGGER = Logger.getLogger(SeatServiceImpl.class);
    private static final String UUID = "([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})";
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

    @Override
    public List<Seat> getSeatsByIdBatch(List<String> seatsNumber) {
        return transactionManager.execute(() -> seatRepository.getSeatsByIdBatch(seatsNumber));
    }

    @Override
    public ArrayList<String> getSeatsId(String seat_id) {
        ArrayList<String> seatIdList = new ArrayList<>();
        Matcher m = Pattern.compile(UUID).matcher(seat_id);
        while (m.find()) {
            seatIdList.add(m.group(1));
        }
        return seatIdList;
    }
}
