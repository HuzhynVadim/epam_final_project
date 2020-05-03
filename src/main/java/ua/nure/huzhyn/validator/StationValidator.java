package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Station;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class StationValidator {
    private static final Logger LOGGER = Logger.getLogger(StationValidator.class);
    private static final String STATION_NAME = "^[a-zA-Zа-яА-яёЁ]{4,25}+$";

    public void isValidStation(Station station) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(station.getStation()) || !ValidatorUtils.isMatch(STATION_NAME, station.getStation())) {
            errors.put("Incorrect format, type something like \"Odessa\"", station.getStation());
        }
        if (!errors.isEmpty()) {
            String message = errors.entrySet().stream()
                    .map(entry -> entry.getKey() + ". Entered data:&nbsp;" + entry.getValue() + ";")
                    .collect(joining("<br/>\n"));
            IncorrectDataException e = new IncorrectDataException(message);
            LOGGER.error(e);
            throw e;
        }
    }
}
