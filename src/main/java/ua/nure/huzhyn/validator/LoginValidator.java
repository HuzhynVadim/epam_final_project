package ua.nure.huzhyn.validator;


import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class LoginValidator {
    private static final Logger LOGGER = Logger.getLogger(LoginValidator.class);
    private static final String EMAIL = "[a-zA-Z0-9._-][a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    private static final String PASSWORD = "[a-zA-Z0-9]{3,16}";


    public void isValid(String email, String password) {
        Map<String, String> errors = new HashMap<>();
        if (!ValidatorUtils.isMatch(EMAIL, email)) {
            errors.put("Incorrect format email entered", email);
        }
        if (!ValidatorUtils.isMatch(PASSWORD, password)) {
            errors.put("Incorrect format password entered", password);
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
