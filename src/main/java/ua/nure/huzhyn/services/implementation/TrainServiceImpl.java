package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.TrainRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.services.TrainService;

import java.util.List;

public class TrainServiceImpl implements TrainService {

    private TrainRepository trainRepository;
    private TransactionManager transactionManager;

    public TrainServiceImpl(TrainRepository trainRepository, TransactionManager transactionManager) {
        this.trainRepository = trainRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public Train getTrainById(String trainId) {
        return transactionManager.execute(() -> trainRepository.getStationById(trainId));
    }

    @Override
    public void updateTrain(Train train) {
        transactionManager.execute(() -> trainRepository.update(train));
    }

    @Override
    public void addTrain(Train train) {
        transactionManager.execute(() -> {
            trainRepository.create(train);
            return null;
        });
    }

    @Override
    public List<Train> getAllTrainList() {
        return transactionManager.execute(() -> trainRepository.getAllTrainList());
    }

    @Override
    public void removeTrain(String trainId) {
        transactionManager.execute(() -> {
            trainRepository.delete(trainId);
            return null;
        });
    }

}
