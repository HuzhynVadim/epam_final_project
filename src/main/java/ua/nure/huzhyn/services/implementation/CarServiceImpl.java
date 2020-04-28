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
    public void updateCar(Car car) {
        transactionManager.execute(() -> carRepository.update(car));
    }

    @Override
    public Car getCarById(String carId) {
        return transactionManager.execute(() -> carRepository.getCarById(carId));
    }

    @Override
    public List<Car> getCarByTrainId(String trainId) {
        return transactionManager.execute(() -> carRepository.getCarByTrainId(trainId));
    }

    @Override
    public void addCar(Car car) {
        transactionManager.execute(() -> {
            carRepository.create(car);
            return null;
        });
    }

    @Override
    public List<Car> getCarByTrainIdAndCarType(String trainId, String carType) {
        return transactionManager.execute(() -> carRepository.getCarByTrainIdAndCarType(trainId,carType));
    }

    @Override
    public void removeCar(String carId) {
        transactionManager.execute(() -> {
            carRepository.delete(carId);
            return null;
        });
    }

    @Override
    public List<CarDto> getAllCarList() {
        List<CarDto> result = transactionManager.execute(() -> carRepository.getAllCarList());
        for (CarDto car : result) {
            calculatePrice(car);
        }
        return result;
    }

    private void calculatePrice(CarDto car) {
        car.setPrice(car.getCarType().getPrice());
    }
}
