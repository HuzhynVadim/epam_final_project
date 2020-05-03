package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;

public interface RoutMappingRepository {
    List<RoutToStationMapping> getAllRoutToStationMappingList();

    void create(RoutToStationMapping entity);

    RoutToStationMapping read(String id);

    boolean update(RoutToStationMapping entity, String stationId);

    boolean delete(String routsId, String stationId);

    List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId);

    MappingInfoDto getMappingInfo(String routsId, String stationId);
}

