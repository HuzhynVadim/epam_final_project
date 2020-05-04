package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Station;

import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link Station}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface StationService {

    /**
     * Return list of {@link Station}
     *
     * @return list of {@link Station}
     */
    List<Station> getAllStationList();

    /**
     * Add {@link Station}
     *
     * @param station {@link Station}
     */
    void addStation(Station station);

    /**
     * Remove {@link Station} by station id
     *
     * @param stationId {@link Station} station id
     */
    void removeStation(String stationId);

    /**
     * Update {@link Station}
     *
     * @param station {@link Station}
     */
    void updateStation(Station station);

    /**
     * Return {@link Station} by id
     *
     * @param stationId {@link Station} station id
     * @return {@link Station} by id
     */
    Station getStationById(String stationId);

}

