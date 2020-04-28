package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.*;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;
import ua.nure.huzhyn.services.*;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.OrderValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/select_cars_and_seats_for_order")
public class SelectCarAndCountSeatsForOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SelectStationAndCarTypeForOrderController.class);
    private OrderService orderService;
    private StationService stationService;
    private RoutService routService;
    private TrainService trainService;
    private RoutMappingService routMappingService;
    private CarService carService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderValidator orderValidator = new OrderValidator();
        Order order = new Order();
        User user = (User) request.getSession().getAttribute(AppContextConstant.SESSION_USER);
        String routsId = request.getParameter("routs_id");
        RoutInfoDto routInfoDto = routService.getRoutById(routsId);
        Train train = trainService.getTrainById(routInfoDto.getTrainId());
        String stationIdA = request.getParameter("arrival_station");
        String stationIdD = request.getParameter("departure_station");
        String carNumber = request.getParameter("car_number");
        Station dispatchStation = stationService.getStationById(stationIdA);
        Station arrivalStation = stationService.getStationById(stationIdD);
        MappingInfoDto arrivalMapping = routMappingService.getMappingInfo(routsId, arrivalStation.getStationId());
        MappingInfoDto dispatchMapping = routMappingService.getMappingInfo(routsId, dispatchStation.getStationId());
        order.setTrainNumber(train.getTrainNumber());
        try {
            order.setCarType(CarType.valueOf(request.getParameter("car_type")));
            order.setCountOfSeats(Integer.parseInt(request.getParameter("count_of_seats")));
            Duration duration = Duration.between(arrivalMapping.getStationArrivalDate(), dispatchMapping.getStationDispatchData());
            order.setTravelTime(String.format("Days: %s Hours: %s Minutes: %s", duration.toDays(),
                    duration.toHours() % 24, duration.toMinutes() % 60));
        } catch (IllegalArgumentException | ArithmeticException | DateTimeException e) {
            LOGGER.error(e);
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        order.setRoutsId(routsId);
        order.setArrivalDate(arrivalMapping.getStationArrivalDate());
        order.setDispatchDate(dispatchMapping.getStationDispatchData());
        order.setUser(user);
        order.setCarNumber(carNumber);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDER_PROCESSING);
        order.setArrivalStation(dispatchStation.getStation());
        order.setDispatchStation(arrivalStation.getStation());
        orderValidator.isValidOrder(order);
        orderService.addOrder(order, routsId);
        String userId = user.getUserId();
        response.sendRedirect("user_account?user_id=" + userId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        String carType = request.getParameter("car_type");
        String trainId = request.getParameter("train_id");
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
        request.setAttribute("routs_id", routsId);
        request.setAttribute("car_type", carType);
        request.setAttribute("train_id", trainId);
        Station departureStationName = stationService.getStationById(departureStation);
        Station arrivalStationName = stationService.getStationById(arrivalStation);
        RoutInfoDto routInfoDto = routService.getRoutById(routsId);
        List<Car> carList = carService.getCarByTrainIdAndCarType(routInfoDto.getTrainId(), carType);
        request.setAttribute("car_list", carList);
        request.setAttribute("departure_station_name", departureStationName.getStation());
        request.setAttribute("arrival_station_name", arrivalStationName.getStation());
        request.getRequestDispatcher("WEB-INF/jsp/selectCarAndCountSeatsForOrder.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));
        routMappingService = (RoutMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        trainService = (TrainService) config.getServletContext().getAttribute((AppContextConstant.TRAIN_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
    }
}
