package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.List;

public interface RoutsRepository extends CRUD<Rout, String> {

    List<RoutInfoDto> getAllRoutList();

    RoutInfoDto getRoutById(String routsId);

    List<Rout> getRouteListWithParameters(String departureStation, String arrivalStation, String departureDate);
}


