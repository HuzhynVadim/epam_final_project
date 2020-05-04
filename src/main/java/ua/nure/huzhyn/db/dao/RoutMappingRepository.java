package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;

/**
 * DAO for the {@link RoutToStationMapping} objects.
 * Besides the basic CRUD methods it provides methods get a list of all routes and their stations in mapping, a
 * specific route with stations in mapping by identifier, a list of stations in mapping
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface RoutMappingRepository {

    void create(RoutToStationMapping entity);

    RoutToStationMapping read(String id);

    boolean update(RoutToStationMapping entity, String stationId);

    boolean delete(String routsId, String stationId);

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
     * @param routsId {@link RoutToStationMapping} rout id
     * @param stationId {@link RoutToStationMapping} station id
     * @return list of {@link RoutToStationMapping} by rout id and station id
     */
    MappingInfoDto getMappingInfo(String routsId, String stationId);
}

