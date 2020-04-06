package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;

public interface RoutToStationMappingService {
    void updateRoutToStationMapping(RoutToStationMapping routToStationMapping, String stationId);

    void addRoutToStationMapping(RoutToStationMapping routToStationMapping);

    void removeRoutToStationMapping(String routsId, String stationId);

    List<RoutToStationMapping> getAllRoutToStationMappingList();

    List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId);

    MappingInfoDto getMappingInfo(String routsId, String stationId);

}
