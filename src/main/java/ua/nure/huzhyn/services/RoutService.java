package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.model.entity.Rout;

import java.time.LocalDateTime;
import java.util.List;

public interface RoutService {

    void addRout(Rout rout);

    void removeRout(String routsId);

    void updateRout(Rout rout);

    List<RoutInfoDto> getAllRoutList();

    RoutInfoDto getRoutById(String routsId);

    List<RoutsOrderDto> getRouteListWithParameters(String departureStation, String arrivalStation, LocalDateTime departureDate);
}

