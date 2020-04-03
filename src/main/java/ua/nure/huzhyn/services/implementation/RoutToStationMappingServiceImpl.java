package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutToStationMappingRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.services.RoutToStationMappingService;

public class RoutToStationMappingServiceImpl implements RoutToStationMappingService {
    private RoutToStationMappingRepository routToStationMappingRepository;
    private TransactionManager transactionManager;


    public RoutToStationMappingServiceImpl(RoutToStationMappingRepository routToStationMappingRepository, TransactionManager transactionManager) {
        this.routToStationMappingRepository = routToStationMappingRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public void updateRoutToStationMapping(RoutToStationMapping routToStationMapping) {
        transactionManager.execute(() -> routToStationMappingRepository.update(routToStationMapping));
    }

    @Override
    public void addRoutToStationMapping(RoutToStationMapping routToStationMapping) {
        transactionManager.execute(() -> {
            routToStationMappingRepository.create(routToStationMapping);
            return null;
        });
    }

    @Override
    public void removeRoutToStationMapping(String routsMId) {
        transactionManager.execute(() -> {
            routToStationMappingRepository.delete(routsMId);
            return null;
        });
    }
}
