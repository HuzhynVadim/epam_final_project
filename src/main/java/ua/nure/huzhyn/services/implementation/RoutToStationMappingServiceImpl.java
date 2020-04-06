package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutToStationMappingRepository;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.services.RoutToStationMappingService;

import java.util.List;

public class RoutToStationMappingServiceImpl implements RoutToStationMappingService {
    private RoutToStationMappingRepository routToStationMappingRepository;
    private TransactionManager transactionManager;


    public RoutToStationMappingServiceImpl(RoutToStationMappingRepository routToStationMappingRepository, TransactionManager transactionManager) {
        this.routToStationMappingRepository = routToStationMappingRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public void updateRoutToStationMapping(RoutToStationMapping routToStationMapping, String stationId) {
        transactionManager.execute(() -> routToStationMappingRepository.update(routToStationMapping, stationId));
    }

    @Override
    public void addRoutToStationMapping(RoutToStationMapping routToStationMapping) {
        transactionManager.execute(() -> {
            routToStationMappingRepository.create(routToStationMapping);
            return null;
        });
    }


    @Override
    public void removeRoutToStationMapping(String routsId, String stationId) {
        transactionManager.execute(() -> {
            routToStationMappingRepository.delete(routsId, stationId);
            return null;
        });
    }

    @Override
    public MappingInfoDto getMappingInfo(String routsId, String stationId) {
        return transactionManager.execute(() -> routToStationMappingRepository.getMappingInfo(routsId, stationId));
    }

    @Override
    public List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId) {
        return transactionManager.execute(() -> routToStationMappingRepository.getAllRoutToStationMappingListById(routsId));
    }

    @Override
    public List<RoutToStationMapping> getAllRoutToStationMappingList() {
        return transactionManager.execute(() -> routToStationMappingRepository.getAllRoutToStationMappingList());
    }
}
