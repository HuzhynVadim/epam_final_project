package ua.nure.huzhyn.validator;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoutMappingValidator {
    private static final Logger LOGGER = Logger.getLogger(RoutMappingValidator.class);
    private static final String STATION_ARRIVAL_DATE = "^(19|20)\\d\\d-(0[1-9]|1[012])-([012]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d)$";
    private static final String STATION_DISPATCH_DATA = "^(19|20)\\d\\d-(0[1-9]|1[012])-([012]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d)$";
    private static final String STATION_ORDER = "(?<![-\\d])(?<!\\d[.,])\\d*[0-9](?![.,]?\\d){1,2}";

    public static boolean contains(final List<MappingInfoDto> array, final LocalDateTime localDateTime) {

        boolean result = false;

        for (MappingInfoDto mappingInfoDto : array) {
            if (mappingInfoDto.getStationArrivalDate().isAfter(localDateTime)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void isValidRoutToStationMapping(RoutToStationMapping routToStationMapping, List<MappingInfoDto> mappingList) {

        Map<String, String> errors = new HashMap<>();

        String arrivalDate = String.valueOf(routToStationMapping.getStationArrivalDate());
        String dispatchDate = String.valueOf(routToStationMapping.getStationDispatchData());

        if (Objects.isNull(routToStationMapping.getStationArrivalDate()) || !ValidatorUtils.isMatch(STATION_ARRIVAL_DATE, arrivalDate)) {
            errors.put("Incorrect format, type something like \"2020-04-06T17:11\"", arrivalDate);
        }
        if (Objects.isNull(routToStationMapping.getStationDispatchData()) || !ValidatorUtils.isMatch(STATION_DISPATCH_DATA, String.valueOf(routToStationMapping.getStationDispatchData()))) {
            errors.put("Incorrect format, type something like \"2020-04-06T17:11\"", String.valueOf(routToStationMapping.getStationDispatchData()));
        }
        if (!ValidatorUtils.isMatch(STATION_ORDER, String.valueOf(routToStationMapping.getOrder()))) {
            errors.put("Incorrect format, type something like \"123\"", String.valueOf(routToStationMapping.getOrder()));
        }
        if (routToStationMapping.getStationArrivalDate().isEqual(routToStationMapping.getStationDispatchData())) {
            errors.put("Incorrect format, \"time should be different\"", arrivalDate + " = " + dispatchDate);
        }
        if (routToStationMapping.getStationArrivalDate().isAfter(routToStationMapping.getStationDispatchData())) {
            errors.put("Incorrect format, \"time should be different\"", arrivalDate + " > " + dispatchDate);
        }
        if (contains(mappingList, routToStationMapping.getStationArrivalDate())) {
            errors.put("Incorrect format, \"time should be different\"", arrivalDate + " already exists");
        }
        if (!errors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                builder.append(entry.getKey())
                        .append("Entered data:&nbsp;")
                        .append(entry.getValue())
                        .append(";<br/>\n");
            }
            IncorrectDataException e = new IncorrectDataException(builder.toString());
            LOGGER.error(e);
            throw e;
        }
    }


    public void isValidUpdateRoutToStationMapping(RoutToStationMapping routToStationMapping) {

        Map<String, String> errors = new HashMap<>();

        String arrivalDate = String.valueOf(routToStationMapping.getStationArrivalDate());
        String dispatchDate = String.valueOf(routToStationMapping.getStationDispatchData());

        if (Objects.isNull(routToStationMapping.getStationArrivalDate()) || !ValidatorUtils.isMatch(STATION_ARRIVAL_DATE, arrivalDate)) {
            errors.put("Incorrect format, type something like \"2020-04-06T17:11\"", arrivalDate);
        }
        if (Objects.isNull(routToStationMapping.getStationDispatchData()) || !ValidatorUtils.isMatch(STATION_DISPATCH_DATA, String.valueOf(routToStationMapping.getStationDispatchData()))) {
            errors.put("Incorrect format, type something like \"2020-04-06T17:11\"", String.valueOf(routToStationMapping.getStationDispatchData()));
        }
        if (!ValidatorUtils.isMatch(STATION_ORDER, String.valueOf(routToStationMapping.getOrder()))) {
            errors.put("Incorrect format, type something like \"123\"", String.valueOf(routToStationMapping.getOrder()));
        }
        if (routToStationMapping.getStationArrivalDate().isEqual(routToStationMapping.getStationDispatchData())) {
            errors.put("Incorrect format, \"time should be different\"", arrivalDate + " = " + dispatchDate);
        }
        if (routToStationMapping.getStationArrivalDate().isAfter(routToStationMapping.getStationDispatchData())) {
            errors.put("Incorrect format, \"time should be different\"", arrivalDate + " > " + dispatchDate);
        }
        if (!errors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                builder.append(entry.getKey())
                        .append("Entered data:&nbsp;")
                        .append(entry.getValue())
                        .append(";<br/>\n");
            }
            IncorrectDataException e = new IncorrectDataException(builder.toString());
            LOGGER.error(e);
            throw e;
        }
    }
}

