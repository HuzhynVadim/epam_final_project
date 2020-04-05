package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;
import java.util.Optional;

public interface RoutToStationMappingRepository {
    List<RoutToStationMapping> getAllRoutToStationMappingList();

    void create(RoutToStationMapping entity);

    Optional<RoutToStationMapping> read(String id);

    boolean update(RoutToStationMapping entity);

    boolean delete(String routsId, String stationId);

    List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId);
}

