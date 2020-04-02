package ua.nure.huzhyn.validator;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isMatch(String regex, String field) {
        return Pattern.compile(regex).matcher(field).find();
    }
}
