package ua.nure.huzhyn.validator;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Seat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class SeatValidator {
    private static final Logger LOGGER = Logger.getLogger(SeatValidator.class);

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
        if ((hasDuplicates(seats) == true) || (seats.isEmpty()) || (Integer.parseInt(countOfSeats) != size)) {
            errors.put("Incorrect format, choose different places", "");
        }


        if (!errors.isEmpty()) {
            String message = errors.entrySet().stream()
                    .map(Map.Entry::getKey)
                    .collect(joining("<br/>\n"));
            IncorrectDataException e = new IncorrectDataException(message);
            LOGGER.error(e);
            throw e;
        }
    }

}
