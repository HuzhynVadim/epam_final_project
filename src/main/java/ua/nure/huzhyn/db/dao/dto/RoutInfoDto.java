package ua.nure.huzhyn.db.dao.dto;

public class RoutInfoDto {
    private String routsId;
    private String trainId;
    private String trainNumber;
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

    public void setCompartmentFreeSeatsCount(int compartmentFreeSeatsCount) {
        this.compartmentFreeSeatsCount = compartmentFreeSeatsCount;
    }

    public int getReservedFreeSeatsCount() {
        return reservedFreeSeatsCount;
    }

    public void setReservedFreeSeatsCount(int reservedFreeSeatsCount) {
        this.reservedFreeSeatsCount = reservedFreeSeatsCount;
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

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
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
}
