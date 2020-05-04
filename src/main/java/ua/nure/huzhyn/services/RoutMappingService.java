package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link RoutToStationMapping}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface RoutMappingService {

    void updateRoutToStationMapping(RoutToStationMapping routToStationMapping, String stationId);

    void addRoutToStationMapping(RoutToStationMapping routToStationMapping);

    void removeRoutToStationMapping(String routsId, String stationId);

    /**
     * Get list of {@link RoutToStationMapping}
     *
     * @return list of {@link RoutToStationMapping}
     */
    List<RoutToStationMapping> getAllRoutToStationMappingList();

    /**
     * Get list of {@link RoutToStationMapping} by rout id
     *
     * @param routsId {@link RoutToStationMapping} rout id
     * @return list of {@link RoutToStationMapping} by rout id
     */
    List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId);

    /**
     * Get list of {@link RoutToStationMapping} by rout id and station id
     *
     * @param routsId   {@link RoutToStationMapping} rout id
     * @param stationId {@link RoutToStationMapping} station id
     * @return list of {@link RoutToStationMapping} by rout id and station id
     */
    MappingInfoDto getMappingInfo(String routsId, String stationId);

}
