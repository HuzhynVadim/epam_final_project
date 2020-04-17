package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.model.entity.Car;

import java.util.List;

public interface CarRepository extends CRUD<Car, String> {

    List<CarDto> getAllCarList();

    Car getCarById(String carId);
}