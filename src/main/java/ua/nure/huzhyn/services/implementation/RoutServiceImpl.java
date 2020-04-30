package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.SeatService;

import java.time.LocalDateTime;
import java.util.*;

public class RoutServiceImpl implements RoutService {
    private CarService carService;
    private RoutsRepository routsRepository;
    private SeatService seatService;
    private TransactionManager transactionManager;


    public RoutServiceImpl(RoutsRepository routsRepository, SeatService seatService, CarService carService, TransactionManager transactionManager) {
        this.routsRepository = routsRepository;
        this.carService = carService;
        this.seatService = seatService;
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
    public Rout read(String id) {
        return transactionManager.execute(() -> routsRepository.read(id).orElse(null));
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

    private RoutsOrderDto toRoutsOrderDto(List<StationDto> stationDtos) {
        RoutsOrderDto routsOrderDto = new RoutsOrderDto();
        routsOrderDto.setStations(stationDtos);

        StationDto stationDto = stationDtos.get(0);
        routsOrderDto.setRoutName(stationDto.getRoutName());
        routsOrderDto.setRoutNumber(stationDto.getRoutNumber());
        routsOrderDto.setRoutsId(stationDto.getRoutsId());
        routsOrderDto.setTrainId(stationDto.getTrainId());
        routsOrderDto.setTrainNumber(stationDto.getTrainNumber());
        int countSeatCommon = seatService.getCountSeatByCarType(stationDto.getTrainId(), CarType.COMMON);
        int countSeatReserved = seatService.getCountSeatByCarType(stationDto.getTrainId(), CarType.RESERVED_SEAT);
        int countSeatCompartment = seatService.getCountSeatByCarType(stationDto.getTrainId(), CarType.COMPARTMENT);
        routsOrderDto.setCommonFreeSeatsCount(countSeatCommon);
        routsOrderDto.setCompartmentFreeSeatsCount(countSeatCompartment);
        routsOrderDto.setReservedFreeSeatsCount(countSeatReserved);

        return routsOrderDto;
    }
}

