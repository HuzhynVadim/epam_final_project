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
    public List<Train> getAllTrain() {
        return transactionManager.execute(() -> trainRepository.getAllTrain());
    }
}
