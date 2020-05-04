package ua.nure.huzhyn.db.dao.dto;

import java.util.List;
import java.util.Objects;

public class RoutsOrderDto {

    private String routsId;
    private String trainId;
    private String trainNumber;
    private List<StationDto> stations;
    private String routName;
    private String routNumber;
    private int commonFreeSeatsCount;
    private int compartmentFreeSeatsCount;
    private int reservedFreeSeatsCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutsOrderDto that = (RoutsOrderDto) o;
        return commonFreeSeatsCount == that.commonFreeSeatsCount &&
                compartmentFreeSeatsCount == that.compartmentFreeSeatsCount &&
                reservedFreeSeatsCount == that.reservedFreeSeatsCount &&
                Objects.equals(routsId, that.routsId) &&
                Objects.equals(trainId, that.trainId) &&
                Objects.equals(trainNumber, that.trainNumber) &&
                Objects.equals(stations, that.stations) &&
                Objects.equals(routName, that.routName) &&
                Objects.equals(routNumber, that.routNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routsId, trainId, trainNumber, stations, routName, routNumber, commonFreeSeatsCount, compartmentFreeSeatsCount, reservedFreeSeatsCount);
    }

    public int getCommonFreeSeatsCount() {
        return commonFreeSeatsCount;
    }

    public void setCommonFreeSeatsCount(int commonFreeSeatsCount) {
        this.commonFreeSeatsCount = commonFreeSeatsCount;
    }

    public int getCompartmentFreeSeatsCount() {
        return compartmentFreeSeatsCount;
    }

    public void setCompartmentFreeSeatsCount(int compartmentFreeSeatsCount) {
        this.compartmentFreeSeatsCount = compartmentFreeSeatsCount;
    }

    public int getReservedFreeSeatsCount() {
        return reservedFreeSeatsCount;
    }

    public void setReservedFreeSeatsCount(int reservedFreeSeatsCount) {
        this.reservedFreeSeatsCount = reservedFreeSeatsCount;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getRoutsId() {
        return routsId;
    }

    public void setRoutsId(String routsId) {
        this.routsId = routsId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getRoutName() {
        return routName;
    }

    public void setRoutName(String routName) {
        this.routName = routName;
    }

    public String getRoutNumber() {
        return routNumber;
    }

    public void setRoutNumber(String routNumber) {
        this.routNumber = routNumber;
    }

    public List<StationDto> getStations() {
        return stations;
    }

    public void setStations(List<StationDto> stations) {
        this.stations = stations;
    }
}
