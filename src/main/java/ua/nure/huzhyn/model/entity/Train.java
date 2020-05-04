package ua.nure.huzhyn.model.entity;


import java.util.Objects;

public class Train {

    private String trainId;
    private String trainNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(trainId, train.trainId) &&
                Objects.equals(trainNumber, train.trainNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, trainNumber);
    }
}
