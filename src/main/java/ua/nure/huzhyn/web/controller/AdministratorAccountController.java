package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.model.entity.enums.UserRole;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.RoutToStationMappingService;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrator_account")
public class AdministratorAccountController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AdministratorAccountController.class);

    private OrderService orderService;
    private UserService userService;
    private StationService stationService;
    private RoutService routService;
    private TrainService trainService;
    private CarService carService;
    private RoutToStationMappingService routToStationMappingService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> orderList = orderService.getAllOrderList();
        request.setAttribute("order_list", orderList);
        List<User> userInfoList = userService.getUserInfo(UserRole.USER.name());
        request.setAttribute("user_list", userInfoList);
        List<Station> stationList = stationService.getAllStationList();
        request.setAttribute("station_list", stationList);
        List<RoutInfoDto> routList = routService.getAllRoutList();
        request.setAttribute("rout_list", routList);
        List<RoutToStationMapping> routToStationMappingList = routToStationMappingService.getAllRoutToStationMappingList();
        request.setAttribute("rout_m_list", routToStationMappingList);
        List<Train> trainList = trainService.getAllTrainList();
        request.setAttribute("train_list", trainList);
        List<CarDto> carList = carService.getAllCarList();
        request.setAttribute("car_list", carList);

        request.getRequestDispatcher("WEB-INF/jsp/administratorAccount.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstant.USER_SERVICE);
        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        trainService = (TrainService) config.getServletContext().getAttribute((AppContextConstant.TRAIN_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
    }
}
