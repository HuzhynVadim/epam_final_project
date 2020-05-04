package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.List;

/**
 * DAO for the {@link Rout} objects.
 * Besides the basic CRUD methods it provides methods gets a list of all routes, a route by identifier,
 * a list of routes with the given parameters
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface RoutsRepository extends CRUD<Rout, String> {

    /**
     * Return list all rout
     *
     * @return list of {@link RoutInfoDto}
     */
    List<RoutInfoDto> getAllRoutList();

    /**
     * Return {@link RoutInfoDto} by rout id
     *
     * @param routsId {@link Rout} rout id
     * @return {@link RoutInfoDto} by rout id
     */
    RoutInfoDto getRoutById(String routsId);

    /**
     * Return list {@link StationDto} by departure station and arrival station user defined
     *
     * @param departureStation {@link StationDto} departure station
     * @param arrivalStation {@link StationDto} arrival station
     * @return list {@link StationDto} by departure station and arrival station
     */
    List<StationDto> getRouteListWithParameters(String departureStation, String arrivalStation);
}


