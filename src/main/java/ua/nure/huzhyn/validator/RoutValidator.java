package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class RoutValidator {
    private static final Logger LOGGER = Logger.getLogger(RoutValidator.class);
    private static final String ROUT_NAME = "[a-zA-Z]*([ \\-_][a-zA-Z]+)*";
    private static final String ROUT_NUMBER = "^[\\d-\\/]{1,10}+$";

    public void isValidRout(Rout rout) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(rout.getRoutName()) || (!ValidatorUtils.isMatch(ROUT_NAME, rout.getRoutName()))) {
            errors.put("Incorrect format, type something like \"Odessa\" or \"Odessa speed\"", rout.getRoutName());
        }
        if (StringUtils.isBlank(rout.getRoutNumber()) || !ValidatorUtils.isMatch(ROUT_NUMBER, rout.getRoutNumber())) {
            errors.put("Incorrect format, type something like \"1-23 or 1/23 or 123\"", rout.getRoutNumber());
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

