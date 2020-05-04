package ua.nure.huzhyn.model.entity;


import java.util.Objects;

public class Seat {

  private String seatId;
  private String carId;
  private int seatNumber;
  private boolean busy;

  public boolean isBusy() {
    return busy;
  }

  public void setBusy(boolean busy) {
    this.busy = busy;
  }

  public String getSeatId() {
    return seatId;
  }

  public void setSeatId(String seatId) {
    this.seatId = seatId;
  }

  public String getCarId() {
    return carId;
  }

  public void setCarId(String carId) {
    this.carId = carId;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Seat seat = (Seat) o;
    return seatNumber == seat.seatNumber &&
            busy == seat.busy &&
            Objects.equals(seatId, seat.seatId) &&
            Objects.equals(carId, seat.carId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatId, carId, seatNumber, busy);
  }
}
