package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class RoutValidator {
    private static final String ROUT_NAME = "[a-zA-Z]{1,25}";

    public void isValidRout(Rout rout) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(rout.getRoutName()) && !ValidatorUtils.isMatch(ROUT_NAME, rout.getRoutName())) {
            errors.put("Incorrect format, type something like \"Odessa\"", rout.getRoutName());
        }
        if (!errors.isEmpty()) {
            String message = errors.entrySet().stream()
                    .map(entry -> entry.getKey() + ". Entered data: " + entry.getValue())
                    .collect(joining("\n"));

            throw new IncorrectDataException(message);
        }
    }
}

