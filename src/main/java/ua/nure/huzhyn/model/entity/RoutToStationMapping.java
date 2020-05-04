package ua.nure.huzhyn.model.entity;


import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutToStationMapping that = (RoutToStationMapping) o;
        return order == that.order &&
                Objects.equals(stationId, that.stationId) &&
                Objects.equals(routsId, that.routsId) &&
                Objects.equals(stationArrivalDate, that.stationArrivalDate) &&
                Objects.equals(stationDispatchData, that.stationDispatchData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationId, routsId, stationArrivalDate, stationDispatchData, order);
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
