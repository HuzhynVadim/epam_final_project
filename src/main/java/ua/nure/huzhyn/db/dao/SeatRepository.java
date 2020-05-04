package ua.nure.huzhyn.db.dao;


import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.util.List;

/**
 * DAO for the {@link Seat} objects.
 * Besides the basic CRUD methods it provides methods removes all seats in a given car, gets the number of seats in a
 * car, gets the number of occupied seats in a car, gets the number of seats in a car by its type and train identifier,
 * gets a list of seats in a car by its identifier, gets a list with all seats by given parameters occupies places and
 * frees up places
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface SeatRepository extends CRUD<Seat, String> {

    /**
     * Delete al sets in car by car id
     *
     * @param carId {@link Seat} car id
     * @return if the seat is successfully removed the true, if not false
     */
    boolean deleteAllSeatsByCarId(String carId);

    /**
     * Сounts empty seats in the car
     *
     * @param carId {@link Seat} car id
     * @return count of all seats in the car
     */
    int getCountSeat(String carId);

    /**
     * Counts busy seats in the car
     *
     * @param carId {@link Seat} car id
     * @return count of all busy seats in the car
     */
    int getCountSeatBusy(String carId);

    /**
     * Сounts seats in the car by car id and car type
     *
     * @param trainId {@link Seat} train id
     * @param carType {@link Seat} car type
     * @return count of all seats in the car by car type and train id
     */
    int getCountSeatByCarType(String trainId, CarType carType);

    /**
     * Get list seats in the car by car id
     *
     * @param carId {@link Seat} car id
     * @return list seats in the car by car id
     */
    List<Seat> getSeatByCarId(String carId);

    /**
     * Get list of seats in the car according to a given list of numbers of seats in the car
     *
     * @param seatsNumber {@link Seat} seat number
     * @return list of seats in the car according to a given list of numbers of seats in the car
     */
    List<Seat> getSeatsByIdBatch(List<String> seatsNumber);

    /**
     * Takes the seats
     *
     * @param seatId {@link Seat} seat id
     */
    void takeTheSeat(String seatId);

    /**
     * Frees up seats
     *
     * @param seatId {@link Seat} seat id
     */
    void freeSeat(String seatId);
}
