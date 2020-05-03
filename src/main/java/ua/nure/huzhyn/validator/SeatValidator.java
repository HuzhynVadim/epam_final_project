package ua.nure.huzhyn.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Seat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class SeatValidator {
    private static final Logger LOGGER = Logger.getLogger(SeatValidator.class);
    private static final String SEAT = "(?<![-\\d])(?<!\\d[.,])\\d*[0-9](?![.,]?\\d){1,2}";

    public static boolean hasDuplicates(List<Seat> seats) {
        for (int i = 0; i < seats.size(); i++) {
            Seat firstPlaceCell = seats.get(i);
            for (int j = i + 1; j < seats.size(); j++) {
                Seat secondPlaceCell = seats.get(j);
                if (firstPlaceCell.getSeatNumber() == secondPlaceCell.getSeatNumber()
                        && firstPlaceCell.getSeatNumber() == secondPlaceCell.getSeatNumber()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void isValidSeat(List<Seat> seats, String countOfSeats) {
        Map<String, String> errors = new HashMap<>();
        int size = seats.size();
        if ((hasDuplicates(seats)) || (seats.isEmpty()) || (Integer.parseInt(countOfSeats) != size)) {
            errors.put("Incorrect format, choose different places", countOfSeats);
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


    public void isValidSeat(String countOfSeats) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isBlank(countOfSeats) || (!ValidatorUtils.isMatch(SEAT, countOfSeats)) || (Integer.parseInt(countOfSeats) == 0)) {
            errors.put("Incorrect format, choose different places", countOfSeats);
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
