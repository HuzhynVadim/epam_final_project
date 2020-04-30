package ua.nure.huzhyn.db.dao;


import ua.nure.huzhyn.model.entity.Seat;

public interface SeatRepository extends CRUD<Seat, String> {

    int getCountSeat(String carId);

    int getCountSeatBusy(String carId);

}
