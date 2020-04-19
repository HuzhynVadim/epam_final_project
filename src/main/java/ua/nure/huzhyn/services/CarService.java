package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.model.entity.Car;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCarList();

    void removeCar(String carId);

    List<Car> getCarByTrainId(String trainId);

    void addCar(Car car);

    Car getCarById(String carId);

    void updateCar(Car car);

}