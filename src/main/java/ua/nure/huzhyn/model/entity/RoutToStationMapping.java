package ua.nure.huzhyn.model.entity;


import java.time.LocalDateTime;

public class RoutToStationMapping {

    private String stationId;
    private String routsId;
    private LocalDateTime stationArrivalDate;
    private LocalDateTime stationDispatchData;
    private int order;


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }


    public String getRoutsId() {
        return routsId;
    }

    public void setRoutsId(String routsId) {
        this.routsId = routsId;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
