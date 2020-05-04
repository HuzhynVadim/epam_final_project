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
