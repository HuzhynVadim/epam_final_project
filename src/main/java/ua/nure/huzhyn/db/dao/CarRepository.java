package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.model.entity.Car;

import java.util.List;

public interface CarRepository extends CRUD<Car, String> {

    List<Car> getCarByTrainId(String trainId);

    List<CarDto> getAllCarList();

    Car getCarById(String carId);

    List<Car> getCarByTrainIdAndCarType(String trainId, String carType);
}