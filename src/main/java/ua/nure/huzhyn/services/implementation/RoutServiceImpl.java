package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.services.RoutService;

import java.util.List;

public class RoutServiceImpl implements RoutService {
    private RoutsRepository routsRepository;
    private TransactionManager transactionManager;


    public RoutServiceImpl(RoutsRepository routsRepository, TransactionManager transactionManager) {
        this.routsRepository = routsRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public void addRout(Rout rout) {
        transactionManager.execute(() -> {
            routsRepository.create(rout);
            return null;
        });
    }


    @Override
    public List<Rout> getRouteListWithParameters(String departureStation, String arrivalStation, String departureDate) {
        return transactionManager.execute(() -> routsRepository.getRouteListWithParameters(departureStation, arrivalStation, departureDate));
    }

    @Override
    public RoutInfoDto getRoutById(String routsId) {
        return transactionManager.execute(() -> routsRepository.getRoutById(routsId));
    }

    @Override
    public void updateRout(Rout rout) {
        transactionManager.execute(() -> routsRepository.update(rout));
    }

    @Override
    public void removeRout(String routsId) {
        transactionManager.execute(() -> {
            routsRepository.delete(routsId);
            return null;
        });
    }

    @Override
    public List<RoutInfoDto> getAllRoutList() {
        return transactionManager.execute(() -> routsRepository.getAllRoutList());
    }
}

