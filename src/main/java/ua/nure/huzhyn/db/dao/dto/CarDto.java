package ua.nure.huzhyn.db.dao.dto;

import ua.nure.huzhyn.model.entity.enums.CarType;

import java.math.BigDecimal;
import java.util.Objects;

public class CarDto {
    private String carId;
    private CarType carType;
    private String carNumber;
    private String trainId;
    private Integer seats;
    private BigDecimal price;
    private String trainNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return Objects.equals(carId, carDto.carId) &&
                carType == carDto.carType &&
                Objects.equals(carNumber, carDto.carNumber) &&
                Objects.equals(trainId, carDto.trainId) &&
                Objects.equals(seats, carDto.seats) &&
                Objects.equals(price, carDto.price) &&
                Objects.equals(trainNumber, carDto.trainNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carType, carNumber, trainId, seats, price, trainNumber);
    }

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

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
}
