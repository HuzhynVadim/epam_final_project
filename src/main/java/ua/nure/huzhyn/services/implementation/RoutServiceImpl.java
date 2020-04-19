package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.RoutService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoutServiceImpl implements RoutService {
    private CarService carService;
    private RoutsRepository routsRepository;
    private TransactionManager transactionManager;


    public RoutServiceImpl(RoutsRepository routsRepository, CarService carService, TransactionManager transactionManager) {
        this.routsRepository = routsRepository;
        this.carService = carService;
        this.transactionManager = transactionManager;
    }


    @Override
    public void addRout(Rout rout) {
        populateFreeSeatsCount(rout);
        transactionManager.execute(() -> {
            routsRepository.create(rout);
            return null;
        });
    }

    @Override
    public List<RoutsOrderDto> getRouteListWithParameters(String departureStation, String arrivalStation, LocalDateTime departureDate) {
        List<StationDto> stations = transactionManager.execute(() -> routsRepository.getRouteListWithParameters(departureStation, arrivalStation));

        Map<String, List<StationDto>> routToStationMap = new HashMap<>();

        for (StationDto stationDto : stations) {
            List<StationDto> routStations = routToStationMap.get(stationDto.getRoutsId());

            if (routStations == null) {
                routStations = new ArrayList<>();
                routToStationMap.put(stationDto.getRoutsId(), routStations);
            }

            routStations.add(stationDto);
        }

        List<RoutsOrderDto> result = new ArrayList<>();

        for (List<StationDto> stationDtos : routToStationMap.values()) {
            StationDto departure = null;
            StationDto arrival = null;
            for (StationDto station : stationDtos) {
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

            if (departure.getOrder() > arrival.getOrder() || departure.getOrder() == arrival.getOrder()) {
                continue;
            }

            if (departure.getStationDispatchData().isAfter(departureDate) || departure.getStationDispatchData().isEqual(departureDate)) {
                result.add(toRoutsOrderDto(stationDtos));
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
        populateFreeSeatsCount(rout);
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

    private RoutsOrderDto toRoutsOrderDto(List<StationDto> stationDtos) {
        RoutsOrderDto routsOrderDto = new RoutsOrderDto();
        routsOrderDto.setStations(stationDtos);

        StationDto stationDto = stationDtos.get(0);
        routsOrderDto.setRoutName(stationDto.getRoutName());
        routsOrderDto.setRoutNumber(stationDto.getRoutNumber());
        routsOrderDto.setRoutsId(stationDto.getRoutsId());
        routsOrderDto.setTrainId(stationDto.getTrainId());
        routsOrderDto.setTrainNumber(stationDto.getTrainNumber());

        return routsOrderDto;
    }

    private void populateFreeSeatsCount(Rout rout) {
        int commonFreeSeatsCount = 0;
        int compartmentFreeSeatsCount = 0;
        int reservedFreeSeatsCount = 0;
        for(Car car : carService.getCarByTrainId(rout.getTrainId())) {
            if (CarType.COMMON.equals(car.getCarType())) {
                commonFreeSeatsCount += car.getSeats();
            }
            if (CarType.COMPARTMENT.equals(car.getCarType())) {
                compartmentFreeSeatsCount += car.getSeats();
            }
            if (CarType.RESERVED_SEAT.equals(car.getCarType())) {
                reservedFreeSeatsCount += car.getSeats();
            }
        }

        rout.setCommonFreeSeatsCount(commonFreeSeatsCount);
        rout.setCompartmentFreeSeatsCount(compartmentFreeSeatsCount);
        rout.setReservedFreeSeatsCount(reservedFreeSeatsCount);
    }
}

