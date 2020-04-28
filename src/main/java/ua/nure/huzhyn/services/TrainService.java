package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Train;

import java.util.List;

public interface TrainService {

    List<Train> getAllTrainList();

    void addTrain(Train train);

    void removeTrain(String trainId);

    void updateTrain(Train train);

    Train getTrainById(String trainId);

    Train getTrainId(String trainNumber);
}

