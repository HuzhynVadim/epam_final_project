package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Order;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OrderValidator {
    private static final Logger LOGGER = Logger.getLogger(OrderValidator.class);
    private static final String SEATS = "(?<![-\\d])(?<!\\d[.,])\\d*[0-9](?![.,]?\\d){1,2}";

    public void isValidOrder(Order order) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(String.valueOf(order.getCountOfSeats())) && !ValidatorUtils.isMatch(SEATS, String.valueOf(order.getCountOfSeats()))) {
            errors.put("Incorrect format, type something like \"1-20\"", String.valueOf(order.getCountOfSeats()));
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
