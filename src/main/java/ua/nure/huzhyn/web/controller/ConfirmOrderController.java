package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.services.RoutMappingService;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.SeatService;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.OrderValidator;
import ua.nure.huzhyn.validator.SeatValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/confirm_order")
public class ConfirmOrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConfirmOrderController.class);
    private OrderService orderService;
    private StationService stationService;
    private TrainService trainService;
    private RoutMappingService routMappingService;
    private CarService carService;
    private UserService userService;
    private SeatService seatService;
    private RoutService routService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderValidator orderValidator = new OrderValidator();
        Order order = new Order();
        User user = (User) request.getSession().getAttribute(AppContextConstant.SESSION_USER);
        String routsId = request.getParameter("routs_id");
        String trainId = request.getParameter("train_id");
        String stationIdA = request.getParameter("arrival_station_id");
        String stationIdD = request.getParameter("departure_station_id");
        String carId = request.getParameter("car_id");
        Car car = carService.getCarById(carId);
        Train train = trainService.getTrainById(trainId);
        Station dispatchStation = stationService.getStationById(stationIdA);
        Station arrivalStation = stationService.getStationById(stationIdD);
        MappingInfoDto arrivalMapping = routMappingService.getMappingInfo(routsId, arrivalStation.getStationId());
        MappingInfoDto dispatchMapping = routMappingService.getMappingInfo(routsId, dispatchStation.getStationId());
        HttpSession session = request.getSession();
        Object locale = session.getAttribute(AppContextConstant.LOCALE);
        try {
            order.setCarType(CarType.valueOf(request.getParameter("car_type")));
            order.setCountOfSeats(Integer.parseInt(request.getParameter("count_of_seats")));
            if (locale == AppContextConstant.LOCALE_EN) {
                Duration duration = Duration.between(arrivalMapping.getStationDispatchData(), dispatchMapping.getStationArrivalDate());
                order.setTravelTime(String.format("Days: %s Hours: %s Minutes: %s", duration.toDays(),
                        duration.toHours() % 24, duration.toMinutes() % 60));
            }
            if (locale == AppContextConstant.LOCALE_RU) {
                Duration duration = Duration.between(arrivalMapping.getStationDispatchData(), dispatchMapping.getStationArrivalDate());
                order.setTravelTime(String.format("Дней: %s Часов: %s Минут: %s", duration.toDays(),
                        duration.toHours() % 24, duration.toMinutes() % 60));
            }
        } catch (IllegalArgumentException | ArithmeticException | DateTimeException e) {
            LOGGER.error(e);
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        order.setRoutsId(routsId);
        order.setArrivalDate(arrivalMapping.getStationDispatchData());
        order.setDispatchDate(dispatchMapping.getStationArrivalDate());
        order.setUser(user);
        order.setTrainNumber(train.getTrainNumber());
        order.setCarNumber(car.getCarNumber());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDER_PROCESSING);
        order.setArrivalStation(dispatchStation.getStation());
        order.setDispatchStation(arrivalStation.getStation());
        String seatId = Arrays.toString(request.getParameterValues("seat_id"));
        ArrayList<String> seatIdList = seatService.getSeatsId(seatId);
        List<Seat> seats = seatService.getSeatsByIdBatch(seatIdList);
        StringBuilder sb = new StringBuilder();
        String number = "";
        for (int i = 0; i <= seats.size() - 1; i++) {
            number = sb.append(seats.get(i).getSeatNumber()).append(" ").toString();
        }
        order.setSeatNumber(number);
        sb = new StringBuilder();
        String id = "";
        for (int i = 0; i <= seats.size() - 1; i++) {
            id = sb.append(seats.get(i).getSeatId()).append(" ").toString();
        }
        order.setSeatId(id);
        orderValidator.isValidOrder(order);
        orderService.addOrder(order, routsId, seats);
        String userId = user.getUserId();
        response.sendRedirect("user_account?user_id=" + userId);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SeatValidator seatValidator = new SeatValidator();
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        String departureStationId = request.getParameter("departure_station_id");
        String arrivalStationId = request.getParameter("arrival_station_id");
        String carType = request.getParameter("car_type");
        String trainId = request.getParameter("train_id");
        String carId = request.getParameter("car_id");
        String countOfSeats = request.getParameter("count_of_seats");
        String userId = request.getParameter("user_id");
        String station1 = request.getParameter("station1");
        String station2 = request.getParameter("station2");
        String travelTime = request.getParameter("travel_time");

        request.setAttribute("station1", station1);
        request.setAttribute("station2", station2);
        request.setAttribute("travel_time", travelTime);
        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.parse(request.getParameter("departure_date"));
        } catch (DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        String[] numbers = request.getParameterValues("seats_number");
        List<String> seatsNumber = Arrays.asList(request.getParameterValues("seats_number"));
        String routsId = request.getParameter("routs_id");
        RoutInfoDto routById = routService.getRoutById(routsId);
        String routName = routById.getRoutName();
        Car car = carService.getCarById(carId);
        String carNumber = car.getCarNumber();
        BigDecimal price = orderService.getPrice(carType, countOfSeats);
        request.setAttribute("price", price);
        request.setAttribute("rout_name", routName);
        request.setAttribute("car_number", carNumber);
        request.setAttribute("departure_station", departureStation);
        request.setAttribute("arrival_station", arrivalStation);
        request.setAttribute("departure_date", departureDate);
        request.setAttribute("departure_station_id", departureStationId);
        request.setAttribute("arrival_station_id", arrivalStationId);
        request.setAttribute("routs_id", routsId);
        request.setAttribute("car_type", carType);
        request.setAttribute("train_id", trainId);
        request.setAttribute("user_id", userId);

        User user = userService.read(userId);
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Train train = trainService.getTrainById(trainId);
        String trainNumber = train.getTrainNumber();
        request.setAttribute("train_number", trainNumber);
        request.setAttribute("first_name", firstName);
        request.setAttribute("last_name", lastName);
        request.setAttribute("car_number", carNumber);
        request.setAttribute("count_of_seats", countOfSeats);
        request.setAttribute("seats_number", seatsNumber);
        request.setAttribute("car_id", carId);
        List<Seat> seats = seatService.getSeatsByIdBatch(seatsNumber);
        request.setAttribute("seats", seats);
        request.setAttribute("seat_id", Arrays.deepToString(numbers));
        seatValidator.isValidSeat(seats, countOfSeats);
        request.getRequestDispatcher("WEB-INF/jsp/confirmOrder.jsp").forward(request, response);
    }


    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
        routMappingService = (RoutMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        trainService = (TrainService) config.getServletContext().getAttribute((AppContextConstant.TRAIN_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
        userService = (UserService) config.getServletContext().getAttribute((AppContextConstant.USER_SERVICE));
        seatService = (SeatService) config.getServletContext().getAttribute((AppContextConstant.SEAT_SERVICE));
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));
        LOGGER.trace("confirm_order Servlet init");

    }
}