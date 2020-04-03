package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.List;

public interface RoutToStationMappingService {
    void updateRoutToStationMapping(RoutToStationMapping routToStationMapping);

    void addRoutToStationMapping(RoutToStationMapping routToStationMapping);

    void removeRoutToStationMapping(String routsMId);

    List<RoutToStationMapping> getAllRoutToStationMappingList();
}
