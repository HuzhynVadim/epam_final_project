package ua.nure.huzhyn.validator;


import org.apache.commons.lang3.StringUtils;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class RegistrationValidator {

    private static final String EMAIL = "[a-zA-Z0-9._-][a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    private static final String PASSWORD = "[a-zA-Z0-9]{3,16}";
    private static final String USER_NAME = "[a-zA-Z]{2,10}";
    private static final String USER_SURNAME = "[a-zA-Z]{2,15}";
    private static final String USER_DATE_OF_BIRTH = "[0-9]{4}-[0-9]{2}\\-[0-9]{2}";
    private static final String USER_PHONE_NUMBER = "\\+[3]{1}[8]{1}[0]{1}[0-9]{9}";


    public void isValidClientRegister(User user) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(user.getEmail()) && !Validator.isMatch(EMAIL, user.getEmail())) {
            errors.put("Incorrect format, type something like \"user@gmail.com\"", user.getEmail());
        }
        if (StringUtils.isBlank(user.getPassword()) && !Validator.isMatch(PASSWORD, user.getPassword())) {
            errors.put("Incorrect format, type something like \"\"", user.getPassword());
        }
        if (StringUtils.isBlank(user.getFirstName()) && !Validator.isMatch(USER_NAME, user.getFirstName())) {
            errors.put("Incorrect format, type something like \"Alexandr\"", user.getFirstName());
        }
        if (StringUtils.isBlank(user.getLastName()) && !Validator.isMatch(USER_SURNAME, user.getLastName())) {
            errors.put("Incorrect format, type something like \"Petrov\"", user.getLastName());
        }
        if (Objects.isNull(user.getBirthDate()) && !Validator.isMatch(USER_DATE_OF_BIRTH, String.valueOf(user.getBirthDate()))) {
            errors.put("Incorrect format, type something like \"12-01-1993\"", String.valueOf(user.getBirthDate()));
        }

        if (StringUtils.isBlank(user.getPhone()) && !Validator.isMatch(USER_PHONE_NUMBER, user.getPhone())) {
            errors.put("Incorrect format, type something like \"+380965467832\"", user.getPhone());
        }

        if (!errors.isEmpty()) {
            String message = errors.entrySet().stream()
                    .map(entry -> entry.getKey() + ". Entered data: " + entry.getValue())
                    .collect(joining("\n"));

            throw new IncorrectDataException(message);
        }
    }
}