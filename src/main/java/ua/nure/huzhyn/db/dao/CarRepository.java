package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.model.entity.Car;

import java.util.List;

/**
 * DAO for the {@link Car} objects.
 * Besides the basic CRUD methods it provides methods to get a list of cars by train ID, get a list of all cars,
 * get a specific car by its identifier, get a car by type of car and train ID.
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface CarRepository extends CRUD<Car, String> {

    /**
     * @param trainId {@link Car} train id
     * @return list of cars
     */
    List<Car> getCarByTrainId(String trainId);

    /**
     * @return list of cars
     */
    List<CarDto> getAllCarList();

    /**
     * @param carId {@link Car} car id
     * @return  {@link Car}
     */
    Car getCarById(String carId);

    /**
     * @param trainId {@link Car} train id
     * @param carType {@link Car} car type
     * @return list of cars
     */
    List<Car> getCarByTrainIdAndCarType(String trainId, String carType);
}