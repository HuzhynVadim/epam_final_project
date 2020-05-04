package ua.nure.huzhyn.model.entity;


import ua.nure.huzhyn.model.entity.enums.CarType;

import java.util.Objects;

public class Car {

    private String carId;
    private CarType carType;
    private String carNumber;
    private String trainId;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                carType == car.carType &&
                Objects.equals(carNumber, car.carNumber) &&
                Objects.equals(trainId, car.trainId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carType, carNumber, trainId);
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

}
