package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Train;

import java.util.List;

public interface TrainRepository extends CRUD<Train, String> {

    List<Train> getAllTrainList();

    Train getTrainById(String trainId);

}
