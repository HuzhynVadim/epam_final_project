package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.RoutToStationMapping;

public interface RoutToStationMappingService {
    void updateRoutToStationMapping(RoutToStationMapping routToStationMapping);

    void addRoutToStationMapping(RoutToStationMapping routToStationMapping);
}
