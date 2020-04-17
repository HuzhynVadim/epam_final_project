package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.model.entity.Station;

import java.util.List;

public interface StationService {

    List<Station> getAllStationList();

    void addStation(Station station);

    void removeStation(String stationId);

    void updateStation(Station station);

    Station getStationById(String stationId);



}

