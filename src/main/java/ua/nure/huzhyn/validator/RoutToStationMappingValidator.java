package ua.nure.huzhyn.validator;

import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class RoutToStationMappingValidator {

    private static final String STATION_ARRIVAL_DATE = "^(19|20)\\d\\d-(0[1-9]|1[012])-([012]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d)$";
    private static final String STATION_DISPATCH_DATA = "^(19|20)\\d\\d-(0[1-9]|1[012])-([012]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d)$";
    private static final String STATION_ORDER = "[0-9]+$";

    public void isValidRoutToStationMapping(RoutToStationMapping routToStationMapping) {

        Map<String, String> errors = new HashMap<>();

        if (Objects.isNull(routToStationMapping.getStationArrivalDate()) && !ValidatorUtils.isMatch(STATION_ARRIVAL_DATE, String.valueOf(routToStationMapping.getStationArrivalDate()))) {
            errors.put("Incorrect format, type something like \"2020-04-06T17:11\"", String.valueOf(routToStationMapping.getStationArrivalDate()));
        }
        if (Objects.isNull(routToStationMapping.getStationDispatchData()) && !ValidatorUtils.isMatch(STATION_DISPATCH_DATA, String.valueOf(routToStationMapping.getStationDispatchData()))) {
            errors.put("Incorrect format, type something like \"2020-04-06T17:11\"", String.valueOf(routToStationMapping.getStationDispatchData()));
        }
        if (Objects.isNull(routToStationMapping.getOrder()) && !ValidatorUtils.isMatch(STATION_ORDER, String.valueOf(routToStationMapping.getOrder()))) {
            errors.put("Incorrect format, type something like \"123\"", String.valueOf(routToStationMapping.getOrder()));
        }

        if (!errors.isEmpty()) {
            String message = errors.entrySet().stream()
                    .map(entry -> entry.getKey() + ". Entered data: " + entry.getValue())
                    .collect(joining("\n"));

            throw new IncorrectDataException(message);
        }
    }
}
