package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.util.List;

public interface SeatService {

    int getCountSeat(String carId);

    int getCountSeatByCarType(String trainId, CarType carType);

    List<Seat> getSeatByCarId(String carId);
}
