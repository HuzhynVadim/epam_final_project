package ua.nure.huzhyn.model.entity;


import java.util.Objects;

public class Station {

    private String stationId;
    private String station;
    private int order;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station1 = (Station) o;
        return order == station1.order &&
                Objects.equals(stationId, station1.stationId) &&
                Objects.equals(station, station1.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationId, station, order);
    }
}
