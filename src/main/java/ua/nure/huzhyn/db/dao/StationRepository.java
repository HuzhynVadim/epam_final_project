package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Station;

import java.util.List;

/**
 * DAO for the {@link Station} objects.
 * Besides the basic CRUD methods it provides methods gets a list of all stations, a station by its identifier
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface StationRepository extends CRUD<Station, String> {

    /**
     * Return list of {@link Station}
     *
     * @return list of {@link Station}
     */
    List<Station> getAllStationList();

    /**
     * Return {@link Station} by id
     *
     * @param stationId {@link Station} station id
     * @return {@link Station} by id
     */
    Station getStationById(String stationId);
}
