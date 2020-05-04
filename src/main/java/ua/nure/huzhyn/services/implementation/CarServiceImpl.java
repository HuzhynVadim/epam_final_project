package ua.nure.huzhyn.services.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.CarRepository;
import ua.nure.huzhyn.db.dao.SeatRepository;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.services.CarService;

import java.util.List;

@SuppressWarnings({"ALL", "FieldMayBeFinal"})
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = Logger.getLogger(CarServiceImpl.class);

    private CarRepository carRepository;
    private SeatRepository seatRepository;
    private TransactionManager transactionManager;

    public CarServiceImpl(CarRepository carRepository, SeatRepository seatRepository, TransactionManager transactionManager) {
        this.carRepository = carRepository;
        this.seatRepository = seatRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void updateCar(CarDto carDto) {
        Car car = getCarFromCarDto(carDto);
        transactionManager.execute(() -> {
            carRepository.update(car);
            int countSeatBusy = seatRepository.getCountSeatBusy(carDto.getCarId());
            int countSeat = seatRepository.getCountSeat(carDto.getCarId());
            if (countSeatBusy == 0) {
                if (countSeat > carDto.getSeats()) {
                    seatRepository.deleteAllSeatsByCarId(carDto.getCarId());
                    for (int i = 1; i <= carDto.getSeats(); i++) {
                        Seat seat = getSeatFromCarDto(carDto, i);
                        seatRepository.create(seat);
                    }
                }
                if (countSeat < carDto.getSeats()) {
                    for (int i = countSeat + 1; i <= carDto.getSeats(); i++) {
                        Seat seat = getSeatFromCarDto(carDto, i);
                        seatRepository.create(seat);
                    }
                }
            } else {
                IncorrectDataException e = new IncorrectDataException("Can`t edit car because there are ordered seats");
                LOGGER.error(e);
                throw e;
            }
            return null;
        });
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
    public void addCar(CarDto carDto) {
        Car car = getCarFromCarDto(carDto);
        transactionManager.execute(() -> {
            String carId = carRepository.create(car);
            carDto.setCarId(carId);
            for (int i = 1; i <= carDto.getSeats(); i++) {
                Seat seat = getSeatFromCarDto(carDto, i);
                seatRepository.create(seat);
            }
            return null;
        });
    }

    @Override
    public List<Car> getCarByTrainIdAndCarType(String trainId, String carType) {
        return transactionManager.execute(() -> carRepository.getCarByTrainIdAndCarType(trainId, carType));
    }

    @Override
    public void removeCar(String carId) {
        transactionManager.execute(() -> {
            seatRepository.deleteAllSeatsByCarId(carId);
            carRepository.delete(carId);
            return null;
        });
    }

    @Override
    public List<CarDto> getAllCarList() {
        List<CarDto> result = transactionManager.execute(carRepository::getAllCarList);
        for (CarDto car : result) {
            int seat = transactionManager.execute(() -> seatRepository.getCountSeat(car.getCarId()));
            car.setSeats(seat);
            calculatePrice(car);
        }
        return result;
    }

    private Seat getSeatFromCarDto(CarDto carDto, int seatNumber) {
        Seat seat = new Seat();
        seat.setCarId(carDto.getCarId());
        seat.setSeatNumber(seatNumber);
        seat.setBusy(false);
        return seat;
    }

    private Car getCarFromCarDto(CarDto carDto) {
        Car car = new Car();
        car.setCarId(carDto.getCarId());
        car.setCarType(carDto.getCarType());
        car.setCarNumber(carDto.getCarNumber());
        car.setTrainId(carDto.getTrainId());
        return car;
    }

    private void calculatePrice(CarDto car) {
        car.setPrice(car.getCarType().getPrice());
    }

}
