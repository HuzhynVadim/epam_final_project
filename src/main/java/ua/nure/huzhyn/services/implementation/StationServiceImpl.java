package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.StationRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.StationService;

import java.util.List;

public class StationServiceImpl implements StationService {

    private StationRepository stationRepository;
    private TransactionManager transactionManager;


    public StationServiceImpl(StationRepository stationRepository, TransactionManager transactionManager) {
        this.stationRepository = stationRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public List<Station> getAllStationList() {
        return transactionManager.execute(() -> stationRepository.getAllStationList());
    }

    @Override
    public void updateStation(Station station) {
        transactionManager.execute(() -> stationRepository.update(station));
    }

    @Override
    public Station getStationById(String stationId) {
        return transactionManager.execute(() -> stationRepository.getStationById(stationId));
    }

    @Override
    public void removeStation(String stationId) {
        transactionManager.execute(() -> {
            stationRepository.delete(stationId);
            return null;
        });
    }

    @Override
    public void addStation(Station station) {
        transactionManager.execute(() -> {
            stationRepository.create(station);
            return null;
        });
    }
}
