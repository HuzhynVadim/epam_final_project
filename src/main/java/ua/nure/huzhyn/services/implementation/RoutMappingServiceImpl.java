package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutMappingRepository;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.services.RoutMappingService;

import java.util.List;

public class RoutMappingServiceImpl implements RoutMappingService {
    private RoutMappingRepository routMappingRepository;
    private TransactionManager transactionManager;


    public RoutMappingServiceImpl(RoutMappingRepository routMappingRepository, TransactionManager transactionManager) {
        this.routMappingRepository = routMappingRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public void updateRoutToStationMapping(RoutToStationMapping routToStationMapping, String stationId) {
        transactionManager.execute(() -> routMappingRepository.update(routToStationMapping, stationId));
    }

    @Override
    public void addRoutToStationMapping(RoutToStationMapping routToStationMapping) {
        transactionManager.execute(() -> {
            routMappingRepository.create(routToStationMapping);
            return null;
        });
    }

    @Override
    public void removeRoutToStationMapping(String routsId, String stationId) {
        transactionManager.execute(() -> {
            routMappingRepository.delete(routsId, stationId);
            return null;
        });
    }

    @Override
    public MappingInfoDto getMappingInfo(String routsId, String stationId) {
        return transactionManager.execute(() -> routMappingRepository.getMappingInfo(routsId, stationId));
    }

    @Override
    public List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId) {
        return transactionManager.execute(() -> routMappingRepository.getAllRoutToStationMappingListById(routsId));
    }

    @Override
    public List<RoutToStationMapping> getAllRoutToStationMappingList() {
        return transactionManager.execute(() -> routMappingRepository.getAllRoutToStationMappingList());
    }
}
