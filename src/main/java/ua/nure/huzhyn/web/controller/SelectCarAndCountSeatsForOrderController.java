package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.services.*;
import ua.nure.huzhyn.util.constants.AppContextConstant;

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

@WebServlet("/select_cars_and_seats_for_order")
public class SelectCarAndCountSeatsForOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SelectCarAndCountSeatsForOrderController.class);
    private RoutService routService;
    private CarService carService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departureStation = request.getParameter("departure_station");
        String departureStationId = request.getParameter("departure_station_id");
        String arrivalStation = request.getParameter("arrival_station");
        String arrivalStationId = request.getParameter("arrival_station_id");
        String carType = request.getParameter("car_type");
        String trainId = request.getParameter("train_id");
        String userId = request.getParameter("user_id");
        String station1 = request.getParameter("station1");
        String station2 = request.getParameter("station2");
        String travelTime = request.getParameter("travel_time");
        request.setAttribute("station1",station1);
        request.setAttribute("station2",station2);
        request.setAttribute("travel_time",travelTime);
        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.parse(request.getParameter("departure_date"));
        } catch (DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        String routsId = request.getParameter("routs_id");
        request.setAttribute("departure_station", departureStation);
        request.setAttribute("departure_station_id", departureStationId);
        request.setAttribute("arrival_station", arrivalStation);
        request.setAttribute("arrival_station_id", arrivalStationId);
        request.setAttribute("departure_date", departureDate);
        request.setAttribute("routs_id", routsId);
        request.setAttribute("car_type", carType);
        request.setAttribute("user_id", userId);
        request.setAttribute("train_id", trainId);
        RoutInfoDto routInfoDto = routService.getRoutById(routsId);
        List<Car> carList = carService.getCarByTrainIdAndCarType(routInfoDto.getTrainId(), carType);
        request.setAttribute("car_list", carList);

        request.getRequestDispatcher("WEB-INF/jsp/selectCarAndCountSeatsForOrder.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
        LOGGER.trace("select_cars_and_seats_for_order Servlet init");
    }
}
