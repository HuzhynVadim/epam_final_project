package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.model.entity.Rout;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link Rout}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface RoutService {

    /**
     * Add {@link Rout}
     *
     * @param rout {@link Rout}
     */
    void addRout(Rout rout);

    /**
     * Remove {@link Rout} bu id
     *
     * @param routsId {@link Rout} rout id
     */
    void removeRout(String routsId);

    /**
     * Update {@link Rout}
     *
     * @param rout {@link Rout}
     */
    void updateRout(Rout rout);

    /**
     * Get list all routs
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
     * @param arrivalStation   {@link StationDto} arrival station
     * @return list {@link StationDto} by departure station and arrival station
     */
    List<RoutsOrderDto> getRouteListWithParameters(String departureStation, String arrivalStation, LocalDateTime departureDate);
}

