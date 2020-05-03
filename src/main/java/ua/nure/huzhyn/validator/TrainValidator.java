package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Train;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class TrainValidator {
    private static final Logger LOGGER = Logger.getLogger(TrainValidator.class);
    private static final String TRAIN_NUMBER = "^(?![-\\/\\\\d])(?<!\\d[.,])0*+([\\d-\\/]*)(?![.,]?\\d)$";

    public void isValidTrain(Train train) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(train.getTrainNumber()) || !ValidatorUtils.isMatch(TRAIN_NUMBER, train.getTrainNumber())) {
            errors.put("Incorrect format, type something like \"123\"", train.getTrainNumber());
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