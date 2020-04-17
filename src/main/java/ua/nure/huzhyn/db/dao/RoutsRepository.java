package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.List;

public interface RoutsRepository extends CRUD<Rout, String> {

    List<RoutInfoDto> getAllRoutList();

    RoutInfoDto getRoutById(String routsId);

    List<RoutsOrderDto> getRouteListWithParameters(String departureStation, String arrivalStation);
}


