package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class SearchValidator {
    private static final Logger LOGGER = Logger.getLogger(SearchValidator.class);
    private static final String DEPARTURE_STATION = "[a-zA-Z]*";
    private static final String ARRIVAL_STATION = "[a-zA-Z]*";

    public void isValidSearch(String departureStation, String arrivalStation) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(departureStation) || (!ValidatorUtils.isMatch(DEPARTURE_STATION, departureStation))) {
            errors.put("Incorrect format, type something like \"Odessa\"", departureStation);
        }
        if (StringUtils.isBlank(arrivalStation) || !ValidatorUtils.isMatch(ARRIVAL_STATION, arrivalStation)) {
            errors.put("Incorrect format, type something like \"Odessa\"", arrivalStation);
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

