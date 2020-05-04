package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.services.SeatService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.SeatValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/select_seats_for_order")
public class SelectSeatsForOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SelectSeatsForOrderController.class);
    private SeatService seatService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SeatValidator seatValidator = new SeatValidator();
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        String departureStationId = request.getParameter("departure_station_id");
        String arrivalStationId = request.getParameter("arrival_station_id");
        String carType = request.getParameter("car_type");
        String trainId = request.getParameter("train_id");
        String userId = request.getParameter("user_id");
        String carId = request.getParameter("car_id");
        String station1 = request.getParameter("station1");
        String station2 = request.getParameter("station2");
        String travelTime = request.getParameter("travel_time");
        request.setAttribute("station1",station1);
        request.setAttribute("station2",station2);
        request.setAttribute("travel_time",travelTime);
        String countOfSeats = request.getParameter("count_of_seats");
        seatValidator.isValidSeat(countOfSeats);
        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.parse(request.getParameter("departure_date"));
        } catch (DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        String routsId = request.getParameter("routs_id");
        request.setAttribute("departure_station", departureStation);
        request.setAttribute("arrival_station", arrivalStation);
        request.setAttribute("departure_date", departureDate);
        request.setAttribute("departure_station_id", departureStationId);
        request.setAttribute("arrival_station_id", arrivalStationId);
        request.setAttribute("routs_id", routsId);
        request.setAttribute("car_type", carType);
        request.setAttribute("train_id", trainId);
        request.setAttribute("user_id", userId);
        request.setAttribute("car_id", carId);
        request.setAttribute("count_of_seats", countOfSeats);
        List<Seat> seatList = seatService.getSeatByCarId(carId);
        request.setAttribute("seat_list", seatList);
        request.getRequestDispatcher("WEB-INF/jsp/selectSeatsForOrder.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        seatService = (SeatService) config.getServletContext().getAttribute((AppContextConstant.SEAT_SERVICE));
        LOGGER.trace("select_seats_for_order Servlet init");
    }
}