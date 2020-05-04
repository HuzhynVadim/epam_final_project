package ua.nure.huzhyn.model.entity;


import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order {

    private String orderId;
    private String trainNumber;
    private CarType carType;
    private BigDecimal price;
    private LocalDateTime arrivalDate;
    private LocalDateTime dispatchDate;
    private User user;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private int countOfSeats;
    private String arrivalStation;
    private String dispatchStation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return countOfSeats == order.countOfSeats &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(trainNumber, order.trainNumber) &&
                carType == order.carType &&
                Objects.equals(price, order.price) &&
                Objects.equals(arrivalDate, order.arrivalDate) &&
                Objects.equals(dispatchDate, order.dispatchDate) &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderDate, order.orderDate) &&
                orderStatus == order.orderStatus &&
                Objects.equals(arrivalStation, order.arrivalStation) &&
                Objects.equals(dispatchStation, order.dispatchStation) &&
                Objects.equals(travelTime, order.travelTime) &&
                Objects.equals(routsId, order.routsId) &&
                Objects.equals(carNumber, order.carNumber) &&
                Objects.equals(seatNumber, order.seatNumber) &&
                Objects.equals(seatId, order.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, trainNumber, carType, price, arrivalDate, dispatchDate, user, orderDate, orderStatus, countOfSeats, arrivalStation, dispatchStation, travelTime, routsId, carNumber, seatNumber, seatId);
    }

    private String travelTime;
    private String routsId;
    private String carNumber;
    private String seatNumber;
    private String seatId;

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getRoutsId() {
        return routsId;
    }

    public void setRoutsId(String routsId) {
        this.routsId = routsId;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDispatchStation() {
        return dispatchStation;
    }

    public void setDispatchStation(String dispatchStation) {
        this.dispatchStation = dispatchStation;
    }

    public int getCountOfSeats() {
        return countOfSeats;
    }

    public void setCountOfSeats(int countOfSeats) {
        this.countOfSeats = countOfSeats;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }


    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public LocalDateTime getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDateTime dispatchDate) {
        this.dispatchDate = dispatchDate;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
