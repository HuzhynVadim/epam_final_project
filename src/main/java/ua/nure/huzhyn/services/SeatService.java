package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link Seat}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface SeatService {

    /**
     * Сounts empty seats in the car
     *
     * @param carId {@link Seat} car id
     * @return count of all seats in the car
     */
    int getCountSeat(String carId);

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
     * Get seats id by "seats batch"
     *
     * @param seatNumber {@link Seat}  seat number
     * @return array seat id
     */
    ArrayList<String> getSeatsId(String seatNumber);
}
