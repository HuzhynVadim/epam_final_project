package ua.nure.huzhyn.model.entity;


import java.util.List;
import java.util.Objects;

public class Rout {

    private String routsId;
    private String trainId;
    private List<Station> stations;
    private String routName;
    private String routNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rout rout = (Rout) o;
        return commonFreeSeatsCount == rout.commonFreeSeatsCount &&
                compartmentFreeSeatsCount == rout.compartmentFreeSeatsCount &&
                reservedFreeSeatsCount == rout.reservedFreeSeatsCount &&
                Objects.equals(routsId, rout.routsId) &&
                Objects.equals(trainId, rout.trainId) &&
                Objects.equals(stations, rout.stations) &&
                Objects.equals(routName, rout.routName) &&
                Objects.equals(routNumber, rout.routNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routsId, trainId, stations, routName, routNumber, commonFreeSeatsCount, compartmentFreeSeatsCount, reservedFreeSeatsCount);
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

    public String getRoutNumber() {
        return routNumber;
    }

    public void setRoutNumber(String routNumber) {
        this.routNumber = routNumber;
    }

    public String getRoutName() {
        return routName;
    }

    public void setRoutName(String routName) {
        this.routName = routName;
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

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
