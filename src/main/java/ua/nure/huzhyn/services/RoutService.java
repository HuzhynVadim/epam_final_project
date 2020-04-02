package ua.nure.huzhyn.services;

import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.List;

public interface RoutService {

    void addRout(Rout rout);

    List<RoutInfoDto> getAllRoutList();

    void removeRout(String routsId);

    void updateRout(Rout rout);

    RoutInfoDto getRoutById(String routsId);
}

