package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class CarValidator {


    private static final String CAR_NUMBER = "[0-9]+$";
    private static final String PRICE = "^\\d+((,|.)\\d{1,2})?$";
    private static final String SEATS = "[0-9]+$";


    public void isValidRout(Car car) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(car.getCarNumber()) && !ValidatorUtils.isMatch(CAR_NUMBER, car.getCarNumber())) {
            errors.put("Incorrect format, type something like \"123\"", car.getCarNumber());
        }
        if (StringUtils.isBlank(String.valueOf(car.getPrice())) && !ValidatorUtils.isMatch(PRICE, String.valueOf(car.getPrice()))) {
            errors.put("Incorrect format, type something like \"123 or 123,4 or 123.4 \"", String.valueOf(car.getPrice()));
        }
        if (StringUtils.isBlank(String.valueOf(car.getSeats())) && !ValidatorUtils.isMatch(SEATS, String.valueOf(car.getSeats()))) {
            errors.put("Incorrect format, type something like \"123\"", String.valueOf(car.getSeats()));
        }

        if (!errors.isEmpty()) {
            String message = errors.entrySet().stream()
                    .map(entry -> entry.getKey() + ". Entered data: " + entry.getValue())
                    .collect(joining("\n"));

            throw new IncorrectDataException(message);
        }
    }
}
