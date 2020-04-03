package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;

public interface RoutToStationMappingRepository extends CRUD<RoutToStationMapping, String> {
    List<RoutToStationMapping> getAllRoutToStationMappingList();
}
