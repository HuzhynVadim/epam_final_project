package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.model.entity.Car;

import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link Car}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface CarService {
    /**
     * Get all car list
     *
     * @return list of {@link CarDto}
     */
    List<CarDto> getAllCarList();

    /**
     * Delete {@link Car}
     *
     * @param carId {@link Car} car id
     */
    void removeCar(String carId);

    /**
     * Get list all cars by train id
     *
     * @param trainId {@link Car} train id
     * @return list all cars by train id
     */
    List<Car> getCarByTrainId(String trainId);

    /**
     * Add {@link Car}
     *
     * @param carDto {@link CarDto}
     */
    void addCar(CarDto carDto);

    /**
     * Get {@link Car} by car id
     *
     * @param carId {@link Car} car id
     * @return {@link Car} by car id
     */
    Car getCarById(String carId);

    /**
     * update {@link Car}
     *
     * @param carDto {@link CarDto}
     */
    void updateCar(CarDto carDto);

    /**
     * Get list all cars by train id and car type
     *
     * @param trainId {@link Car} train id
     * @param carType {@link Car} car type
     * @return list all cars by train id and car type
     */
    List<Car> getCarByTrainIdAndCarType(String trainId, String carType);

}
