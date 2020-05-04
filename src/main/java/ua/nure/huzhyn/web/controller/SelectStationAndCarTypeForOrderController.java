package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.RoutMappingService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/select_station_and_car_type_for_order")
public class SelectStationAndCarTypeForOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SelectStationAndCarTypeForOrderController.class);
    private RoutMappingService routMappingService;
    private CarService carService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        String trainId = request.getParameter("train_id");
        String userId = request.getParameter("user_id");

        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.parse(request.getParameter("departure_date"));
        } catch (DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        request.setAttribute("departure_station", departureStation);
        request.setAttribute("arrival_station", arrivalStation);
        request.setAttribute("departure_date", departureDate);
        request.setAttribute("user_id", userId);
        String routsId = request.getParameter("routs_id");
        List<MappingInfoDto> allRoutToStationMappingListById = routMappingService.getAllRoutToStationMappingListById(routsId);
        request.setAttribute("station_list", allRoutToStationMappingListById);
        List<Car> allCarList = carService.getCarByTrainId(trainId);
        Set<CarType> carSet = new HashSet<>();
        for (Car car : allCarList) {
            carSet.add(car.getCarType());
        }
        request.setAttribute("train_id", trainId);
        String station1 = request.getParameter("station1");
        String station2 = request.getParameter("station2");
        String travelTime = request.getParameter("travel_time");
        request.setAttribute("station1",station1);
        request.setAttribute("station2",station2);
        request.setAttribute("travel_time",travelTime);
        request.setAttribute("carTypeList", carSet);
        request.setAttribute("routs_id", routsId);
        request.getRequestDispatcher("WEB-INF/jsp/selectStationAndCarTypeForOrder.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        routMappingService = (RoutMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
        LOGGER.trace("select_station_and_car_type_for_order Servlet init");
    }
}
