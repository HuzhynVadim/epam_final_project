package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Station;

import java.util.List;

public interface StationRepository extends CRUD<Station, String> {

    List<Station> getAllStationList();

    Station getStationById(String stationId);
}
