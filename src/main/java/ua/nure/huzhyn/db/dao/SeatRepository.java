package ua.nure.huzhyn.db.dao;


import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.util.List;

public interface SeatRepository extends CRUD<Seat, String> {

    boolean deleteAllSeatsByCarId(String carId);

    int getCountSeat(String carId);

    int getCountSeatBusy(String carId);

    int getCountSeatByCarType(String trainId, CarType carType);

    List<Seat> getSeatByCarId(String carId);

    List<Seat> getSeatsByIdBatch(List<String> seatsNumber);

    void takeTheSeat(String seatId);

    void freeSeat(String seatId);
}
