package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.services.RoutMappingService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/detail_rout")
public class DetailRoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DetailRoutController.class);

    private RoutMappingService routMappingService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routsId = request.getParameter("routs_id");
        String userId = request.getParameter("user_id");
        String departureStation = request.getParameter("departure_station");
        String arrivalStation = request.getParameter("arrival_station");
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
        request.setAttribute("routs_id", routsId);
        List<MappingInfoDto> AllRoutToStationMappingListById = routMappingService.getAllRoutToStationMappingListById(routsId);
        request.setAttribute("rout_m_list", AllRoutToStationMappingListById);
        HttpSession session = request.getSession();
        request.setAttribute("language", session.getAttribute(AppContextConstant.LOCALE));
        request.getRequestDispatcher("WEB-INF/jsp/detailRout.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {

        routMappingService = (RoutMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));
        LOGGER.trace("detail_rout Servlet init");

    }
}