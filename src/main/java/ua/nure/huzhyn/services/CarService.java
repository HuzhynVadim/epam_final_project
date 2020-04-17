package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.model.entity.Car;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCarList();
}
