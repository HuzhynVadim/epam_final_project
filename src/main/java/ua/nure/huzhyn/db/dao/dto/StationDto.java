package ua.nure.huzhyn.db.dao.dto;

import java.time.LocalDateTime;

public class StationDto {

    private String stationId;
    private String station;
    private int order;
    private LocalDateTime stationArrivalDate;
    private LocalDateTime stationDispatchData;
    private String routName;
    private String routNumber;
    private String routsId;
    private String trainId;
    private String trainNumber;
    private int commonFreeSeatsCount;
    private int compartmentFreeSeatsCount;
    private int reservedFreeSeatsCount;

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

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public LocalDateTime getStationArrivalDate() {
        return stationArrivalDate;
    }

    public void setStationArrivalDate(LocalDateTime stationArrivalDate) {
        this.stationArrivalDate = stationArrivalDate;
    }

    public LocalDateTime getStationDispatchData() {
        return stationDispatchData;
    }

    public void setStationDispatchData(LocalDateTime stationDispatchData) {
        this.stationDispatchData = stationDispatchData;
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

    public String getRoutsId() {
        return routsId;
    }

    public void setRoutsId(String routsId) {
        this.routsId = routsId;
    }
}
