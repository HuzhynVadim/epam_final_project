package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.exception.IncorrectDataException;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class CarValidator {
    private static final Logger LOGGER = Logger.getLogger(CarValidator.class);
    private static final String CAR_NUMBER = "[0-9]+$";

    public void isValidCar(CarDto carDto) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(carDto.getCarNumber()) || !ValidatorUtils.isMatch(CAR_NUMBER, carDto.getCarNumber())) {
            errors.put("Incorrect format, type something like \"123\"", carDto.getCarNumber());
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
