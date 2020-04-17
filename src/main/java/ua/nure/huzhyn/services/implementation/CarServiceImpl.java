package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.CarRepository;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.services.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private TransactionManager transactionManager;

    public CarServiceImpl(CarRepository carRepository, TransactionManager transactionManager) {
        this.carRepository = carRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public List<CarDto> getAllCarList() {
        return transactionManager.execute(() -> carRepository.getAllCarList());
    }
}
