package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.RoutToStationMappingService;
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

@WebServlet("/make_order")
public class MakeOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MakeOrderController.class);
    private RoutToStationMappingService routToStationMappingService;
    private CarService carService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        String trainId = request.getParameter("train_id");
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
        String routsId = request.getParameter("routs_id");
        List<MappingInfoDto> allRoutToStationMappingListById = routToStationMappingService.getAllRoutToStationMappingListById(routsId);
        request.setAttribute("station_list", allRoutToStationMappingListById);
        List<Car> allCarList = carService.getCarByTrainId(trainId);
        Set<CarType> carSet = new HashSet<>();
        for (Car car : allCarList) {
            carSet.add(car.getCarType());
        }
        request.setAttribute("train_id", trainId);
        request.setAttribute("carTypeList", carSet);
        request.setAttribute("routs_id", routsId);
        request.getRequestDispatcher("WEB-INF/jsp/orderPage.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
    }
}
