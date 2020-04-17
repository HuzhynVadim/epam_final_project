package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.RoutService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    public List<RoutsOrderDto> getRouteListWithParameters(String departureStation, String arrivalStation, LocalDateTime departureDate) {
        List<RoutsOrderDto> routs = transactionManager.execute(() -> routsRepository.getRouteListWithParameters(departureStation, arrivalStation));

        List<RoutsOrderDto> result = new ArrayList<>();

        for (RoutsOrderDto rout : routs) {
            StationDto departure = null;
            StationDto arrival = null;
            for (StationDto station : rout.getStations()) {
                if (station.getStation().equalsIgnoreCase(departureStation)) {
                    departure = station;
                }

                if (station.getStation().equalsIgnoreCase(arrivalStation)) {
                    arrival = station;
                }
            }

            if (Objects.isNull(departure) || Objects.isNull(arrival)) {
                continue;
            }

            if (departure.getOrder() < arrival.getOrder()) {
                continue;
            }

            if (departure.getStationArrivalDate().isAfter(departureDate) || departure.getStationArrivalDate().isEqual(departureDate)) {
                result.add(rout);
            }
        }

        return result;
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

