package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Rout;

import java.util.HashMap;
import java.util.Map;

public class RoutValidator {
    private static final Logger LOGGER = Logger.getLogger(RoutValidator.class);
    private static final String ROUT_NAME = "[a-zA-Zа-яА-яёЁ]*([ \\-_][a-zA-Zа-яА-яёЁ]+)*";
    private static final String ROUT_NUMBER = "^(?![-\\/\\\\d])(?<!\\d[.,])0*+([\\d-\\/]*)(?![.,]?\\d)$";

    public void isValidRout(Rout rout) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(rout.getRoutName()) || (!ValidatorUtils.isMatch(ROUT_NAME, rout.getRoutName()))) {
            errors.put("Incorrect format, type something like \"Odessa\" or \"Odessa speed\"", rout.getRoutName());
        }
        if (StringUtils.isBlank(rout.getRoutNumber()) || !ValidatorUtils.isMatch(ROUT_NUMBER, rout.getRoutNumber())) {
            errors.put("Incorrect format, type something like \"1-23 or 1/23 or 123\"", rout.getRoutNumber());
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

