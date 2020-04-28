package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.RoutsOrderDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.SearchValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@WebServlet("/search_rout_for_order" )
public class SearchRoutForOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchRoutForOrderController.class);
    private RoutService routService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SearchValidator searchValidator = new SearchValidator();
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.parse(request.getParameter("departure_date"));
        } catch (DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        List<CarType> carTypeList = new ArrayList<>(EnumSet.allOf(CarType.class));
        searchValidator.isValidSearch(departureStation,arrivalStation);
        request.setAttribute("carTypeList", carTypeList);
        List<RoutsOrderDto> routList = routService.getRouteListWithParameters(departureStation, arrivalStation, departureDate);
        request.setAttribute("rout_list", routList);
        request.setAttribute("departure_station", departureStation);
        request.setAttribute("arrival_station", arrivalStation);
        request.setAttribute("departure_date", departureDate);
        request.getRequestDispatcher("WEB-INF/jsp/searchRoutForOrder.jsp").forward(request, response);
    }


    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));
    }
}
