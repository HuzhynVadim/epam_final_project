package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.RoutToStationMappingService;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/selection_of_the_route_for_ordering")
public class selectionOfTheRouteForOrderingController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(selectionOfTheRouteForOrderingController.class);
    private OrderService orderService;
    private UserService userService;
    private StationService stationService;
    private RoutService routService;
    private RoutToStationMappingService routToStationMappingService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        LocalDateTime departureDate = LocalDateTime.parse(request.getParameter("departure_date"));
        List<RoutsOrderDto> routList = routService.getRouteListWithParameters(departureStation, arrivalStation, departureDate);
        request.setAttribute("rout_list", routList);
        request.getRequestDispatcher("WEB-INF/jsp/selectionOfTheRouteForOrdering.jsp").forward(request, response);
    }


    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstant.USER_SERVICE);
        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));

    }
}
