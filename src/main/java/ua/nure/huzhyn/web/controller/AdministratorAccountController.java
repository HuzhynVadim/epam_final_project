package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.model.entity.*;
import ua.nure.huzhyn.model.entity.enums.UserRole;
import ua.nure.huzhyn.services.*;
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
    private RoutMappingService routMappingService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> orderList = orderService.getAllOrderList();
        for (int i = 0; i <= orderList.size() - 1; i++) {
            RoutInfoDto routInfoDto = routService.getRoutById(orderList.get(i).getRoutsId());
            orderList.get(i).setRoutsId(routInfoDto.getRoutName());
        }
        request.setAttribute("order_list", orderList);
        List<User> userInfoList = userService.getUserInfo(UserRole.USER.name());
        request.setAttribute("user_list", userInfoList);
        List<Station> stationList = stationService.getAllStationList();
        request.setAttribute("station_list", stationList);
        List<RoutInfoDto> routList = routService.getAllRoutList();
        request.setAttribute("rout_list", routList);
        List<RoutToStationMapping> routToStationMappingList = routMappingService.getAllRoutToStationMappingList();
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
        routMappingService = (RoutMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        trainService = (TrainService) config.getServletContext().getAttribute((AppContextConstant.TRAIN_SERVICE));
        carService = (CarService) config.getServletContext().getAttribute((AppContextConstant.CARS_SERVICE));
        LOGGER.trace("administrator_account Servlet init");
    }
}
